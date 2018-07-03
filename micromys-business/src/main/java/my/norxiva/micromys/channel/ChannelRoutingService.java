package my.norxiva.micromys.channel;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.*;
import my.norxiva.micromys.channel.query.ChannelBankLimit;
import my.norxiva.micromys.channel.query.ChannelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ChannelRoutingService {

    private ChannelConfigManager channelConfigManager;
    private ChannelBankLimitManager channelBankLimitManager;

    @Autowired
    public ChannelRoutingService(ChannelConfigManager channelConfigManager, ChannelBankLimitManager channelBankLimitManager) {
        this.channelConfigManager = channelConfigManager;
        this.channelBankLimitManager = channelBankLimitManager;
    }

    public ChannelConfig route(TransactionType transactionType, TransactionCategory transactionCategory,
                        BankAcronym bankAcronym, BigDecimal amount) {
        List<ChannelConfig> configs = channelConfigManager
                .findAll()
                .stream()
                .filter(
                        it -> it.getSupportedTransactionTypes().contains(transactionType)
                                && it.getSupportedBanks().contains(bankAcronym))
                .collect(Collectors.toList());

        if (configs.isEmpty()) {
            log.error("no available channel config found. type '{}', bank '{}'", transactionCategory, bankAcronym);
            throw new ApiException(ApiCode.C010001);
        }

        List<String> channelIds = configs.stream().map(ChannelConfig::getId).collect(Collectors.toList());

        List<ChannelBankLimit> limits = channelBankLimitManager
                .find(transactionType, transactionCategory, bankAcronym);

        if (limits.isEmpty()) {
            log.error("no available channel bank limit config found. type '{}', category '{}', bank '{}'",
                    transactionType, transactionCategory, bankAcronym);
            throw new ApiException(ApiCode.C010002);
        }

        List<ChannelBankLimit> filteredLimits = limits.stream()
                .filter(
                        it -> it.getMaxLimit().compareTo(amount) >= 0
                                && it.getMinLimit().compareTo(amount) <= 0
                                && channelIds.contains(it.getChannelId()))
                .collect(Collectors.toList());

        if (filteredLimits.isEmpty()) {
            log.error("no available channel bank limit config found. amount '{}', type '{}', category '{}', bank '{}'",
                    amount, transactionType, transactionCategory, bankAcronym);
            throw new ApiException(ApiCode.C010003);
        }

        List<String> filteredChannelIds = filteredLimits.stream().map(ChannelBankLimit::getChannelId)
                .collect(Collectors.toList());

        Optional<ChannelConfig> optional = configs
                .stream()
                .filter(it -> filteredChannelIds.contains(it.getId()))
                .max(Comparator.comparing(ChannelConfig::getPriority));

        if (!optional.isPresent()) {
            log.error("no available channel bank limit config found. amount '{}', type '{}', category '{}', bank '{}'",
                    amount, transactionType, transactionCategory, bankAcronym);
            throw new ApiException(ApiCode.C010003);
        }

        return optional.get();
    }

}
