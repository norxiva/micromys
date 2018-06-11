package my.norxiva.micromys.order.api;

import lombok.Getter;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.OrderStatus;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionType;

import java.math.BigDecimal;

@Getter
public class OrderExecutedEvent extends AbstractOrderEvent {

    @lombok.Builder(builderClassName = "Builder")
    private OrderExecutedEvent(String id,
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
