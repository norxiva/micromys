package my.norxiva.micromys.order.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.norxiva.micromys.AccountType;
import my.norxiva.micromys.IdType;

@Getter
@Setter
@ToString
public class PaymentAccount {
    private String accountNo;
    private String bankAccountNo;
    private String bankAcronym;
    private AccountType accountType;
    private String idNo;
    private IdType idType;
    private String bankReservedPhone;
}
