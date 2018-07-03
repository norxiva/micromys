package my.norxiva.micromys;

import lombok.Getter;

public enum ChannelStatus {
    CREATED("已创建"),
    ENABLED("已启用"),
    MAINTAINED("已维护");

    @Getter
    private String name;

    ChannelStatus(String name) {
        this.name = name;
    }
}
