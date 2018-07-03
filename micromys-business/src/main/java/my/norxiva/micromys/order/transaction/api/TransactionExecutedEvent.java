package my.norxiva.micromys.order.transaction.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import my.norxiva.micromys.ChannelType;
import my.norxiva.micromys.TransactionCategory;
import my.norxiva.micromys.TransactionStatus;
import my.norxiva.micromys.TransactionType;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class TransactionExecutedEvent extends AbstractTransactionEvent {

    public TransactionExecutedEvent(String id,
                                    String orderNo,
                                    String serial,
                                    BigDecimal amount,
                                    TransactionCategory transactionCategory,
                                    TransactionType transactionType,
                                    ChannelType channelType,
                                    TransactionStatus status,
                                    String code,
                                    String message) {
        super(id, orderNo, serial, amount, transactionCategory, transactionType, channelType, status, code, message);
    }
}
