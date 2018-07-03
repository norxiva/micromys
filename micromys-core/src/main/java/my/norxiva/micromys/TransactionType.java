package my.norxiva.micromys;

public enum TransactionType {
    RECHARGE("充值"),
    WITHDRAW("提现"),
    WITHHOLD("代扣"),
    PAY("代付"),
    TRANSFER("转账"),
    REFUND("退款"),
    BINDING("快捷绑卡"),
    VERIFICATION("验证");

    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
