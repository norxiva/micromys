package my.norxiva.micromys.order.api;

import lombok.*;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.OrderStatus;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.math.BigDecimal;

@SuppressWarnings("WeakerAccess")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class AbstractOrderEvent {

    @TargetAggregateIdentifier
    private String id;
    private String no;
    private BigDecimal amount;
    private TransactionCategory transactionCategory;
    private TransactionType transactionType;
    private ChannelType channelType;
    private OrderStatus status;
    private String code;
    private String message;
}
