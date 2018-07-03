package my.norxiva.micromys;

public enum TransactionEventStatus {
    CREATED("已创建"),
    EXECUTED("已执行"),
    SUCCEED("成功"),
    FAILED("失败");

    private String name;

    TransactionEventStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
