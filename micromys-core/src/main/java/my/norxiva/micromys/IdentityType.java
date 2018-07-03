package my.norxiva.micromys;

import lombok.Getter;

public enum IdentityType {
    THREE_FACTORS("三要素"),
    FOUR_FACTORS("四要素"),
    FIVE_FACTORS("五要素");

    @Getter
    private String name;

    IdentityType(String name) {
        this.name = name;
    }
}
