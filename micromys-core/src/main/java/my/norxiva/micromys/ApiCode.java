package my.norxiva.micromys;

public enum ApiCode {

    C000000("000000", "成功"),
    C010001("010001", "无适配的渠道配置"),
    C010002("010002", "无适配的渠道限额配置"),
    C010003("010003", "无适配金额的渠道限额配置"),
    C100000("100000", "失败");

    ApiCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
