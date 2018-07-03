package my.norxiva.micromys;

import lombok.Getter;

public enum BankCardType {
    DEBIT("借记卡"),
    CREDIT("信用卡"),
    QUASI_CREDIT("准贷记卡");

    @Getter
    private String name;

    BankCardType(String name) {
        this.name = name;
    }
}
