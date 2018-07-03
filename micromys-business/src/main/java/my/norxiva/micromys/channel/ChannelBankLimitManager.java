package my.norxiva.micromys.channel;

import lombok.extern.slf4j.Slf4j;
import my.norxiva.micromys.BankAcronym;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import my.norxiva.micromys.channel.query.ChannelBankLimit;
import my.norxiva.micromys.channel.query.ChannelBankLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ChannelBankLimitManager {

    private ChannelBankLimitRepository repository;

    @Autowired
    public ChannelBankLimitManager(ChannelBankLimitRepository repository) {
        this.repository = repository;
    }

    public ChannelBankLimit save(ChannelBankLimit limit) {
        return repository.save(limit);
    }

    public ChannelBankLimit get(String id) {
        return repository.findOne(id);
    }

    public List<ChannelBankLimit> find(TransactionType transactionType,
                                       TransactionCategory transactionCategory,
                                       BankAcronym bankAcronym) {
        return repository.findByTransactionTypeAndTransactionCategoryAndBankAcronym(transactionType,
                transactionCategory, bankAcronym);
    }

    public void delete(String id) {
        repository.delete(id);
    }


}
