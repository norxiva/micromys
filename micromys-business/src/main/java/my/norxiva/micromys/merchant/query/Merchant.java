package my.norxiva.micromys.merchant.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.micromys.BankAcronym;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.MerchantStatus;
import my.norxiva.micromys.TransactionType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * merchant configuration used for channel routing
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Document
public class Merchant {

    @Id
    private String no;

    @Version
    private Long version;

    @Indexed
    private String parentNo;

    @NotBlank
    private String name;
    private String desc;
    private MerchantStatus status;

    private Map<TransactionType, Boolean> supportedTransactionTypes = new HashMap<>();
    private Map<BankAcronym, Boolean> supportedBanks = new HashMap<>();
    private Map<ChannelType, ChannelTypeConfig> supportedChannelTypes = new HashMap<>();

    public Boolean transactionTypeIsSupported(TransactionType transactionType) {
        return Optional.ofNullable(supportedTransactionTypes.get(transactionType)).orElse(false);
    }

    public Boolean bankIsSupported(BankAcronym bankAcronym) {
        return Optional.ofNullable(supportedBanks.get(bankAcronym)).orElse(false);
    }

    public ChannelTypeConfig getSupportedChannelType(ChannelType channelType) {
        return Optional.ofNullable(supportedChannelTypes.get(channelType)).orElse(new ChannelTypeConfig());
    }

    public Boolean channelTypeIsSupported(ChannelType channelType) {
        return getSupportedChannelType(channelType).isSupported();
    }

    public Boolean channelIdIsSupported(ChannelType channelType, String channelId) {
        ChannelTypeConfig config = getSupportedChannelType(channelType);
        if (config.isGlobal()) return true;
        return config.getSupportedChannelIds().contains(channelId);
    }

    @Getter
    @Setter
    @ToString
    public static class ChannelTypeConfig {
        private boolean supported;
        private boolean global;

        /**
         * used if global is false.
         */
        private List<String> supportedChannelIds = new ArrayList<>();
    }

}
