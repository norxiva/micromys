package my.norxiva.micromys;

public enum TransactionStatus {

    CREATED("已创建"),
    PROCESSING("处理中"),
    SUCCEED("成功"),
    FAILED("失败");

    private String name;

    TransactionStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
