package my.norxiva.micromys;

import lombok.Getter;

public enum AccountType {
    CORPORATE("对公"),
    PRIVATE("对私");

    @Getter
    private String name;

    AccountType(String name) {
        this.name = name;
    }


}
