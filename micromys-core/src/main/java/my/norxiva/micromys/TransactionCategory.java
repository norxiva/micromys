package my.norxiva.micromys;

public enum TransactionCategory {
    DIRECT("直连"),
    GATEWAY("网关");

    private String name;

    TransactionCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
