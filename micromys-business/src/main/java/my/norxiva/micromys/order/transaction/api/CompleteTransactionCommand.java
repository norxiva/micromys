package my.norxiva.micromys.order.transaction.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class CompleteTransactionCommand extends AbstractTransactionCommand {
}
