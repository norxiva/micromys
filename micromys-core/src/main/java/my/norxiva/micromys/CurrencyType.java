package my.norxiva.micromys;

import lombok.Getter;

public enum CurrencyType {
    CNY("人民币"),
    USD("美元");

    @Getter
    private String name;

    CurrencyType(String name) {
        this.name = name;
    }
}
