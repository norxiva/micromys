package my.norxiva.micromys;

public enum  OrderEventStatus {
    CREATED("已创建"),
    PREPARED("已下单"),
    EXECUTED("已执行"),
    CONFIRMED("已确认"),
    SUCCEED("成功"),
    FAILED("失败");

    private String name;

    OrderEventStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
