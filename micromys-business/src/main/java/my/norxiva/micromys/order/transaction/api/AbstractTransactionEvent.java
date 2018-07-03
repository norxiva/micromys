package my.norxiva.micromys.order.transaction.api;


import lombok.*;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionStatus;
import my.norxiva.micromys.TransactionType;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class AbstractTransactionEvent {

    @TargetAggregateIdentifier
    private String id;
    private String orderNo;
    private String serial;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
    private TransactionStatus status;
    private String code;
    private String message;
}
