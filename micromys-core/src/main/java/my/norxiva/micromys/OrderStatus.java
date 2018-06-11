package my.norxiva.micromys;

public enum OrderStatus {
    CREATED("已创建"),
    PREPARED("已下单"),
    PROCESSING("处理中"),
    SUCCESS("成功"),
    FAILED("失败");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
