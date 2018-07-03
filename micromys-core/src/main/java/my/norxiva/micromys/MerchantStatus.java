package my.norxiva.micromys;

import lombok.Getter;

public enum MerchantStatus {
    CREATED("已创建"),
    ACTIVED("已激活"),
    SUSPENDED("已挂起");

    @Getter
    private String name;

    MerchantStatus(String name) {
        this.name = name;
    }
}
