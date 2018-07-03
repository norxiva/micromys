package my.norxiva.micromys.channel.query;

import my.norxiva.micromys.BankAcronym;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChannelBankLimitRepository extends MongoRepository<ChannelBankLimit, String> {
    List<ChannelBankLimit> findByTransactionTypeAndTransactionCategoryAndBankAcronym(TransactionType transactionType,
                                                                                     TransactionCategory transactionCategory,
                                                                                     BankAcronym bankAcronym);
}
