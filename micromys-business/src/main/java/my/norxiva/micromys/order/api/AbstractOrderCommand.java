package my.norxiva.micromys.order.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@ToString
public abstract class AbstractOrderCommand {

    @TargetAggregateIdentifier
    private String id;
    private String no;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
}
