package my.norxiva.micromys.order.transaction.api;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public abstract class AbstractTransactionCommand {

    @TargetAggregateIdentifier
    private String id;
    private String orderNo;
    private String serial;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
}
