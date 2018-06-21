package my.norxiva.micromys.order.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.OrderStatus;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class OrderConfirmedEvent extends AbstractOrderEvent {
    public OrderConfirmedEvent(String id,
                               String no,
                               BigDecimal amount,
                               TransactionCategory transactionCategory,
                               TransactionType transactionType,
                               ChannelType channelType,
                               OrderStatus status,
                               String code,
                               String message) {
        super(id, no, amount, transactionCategory, transactionType, channelType, status, code, message);
    }
}
