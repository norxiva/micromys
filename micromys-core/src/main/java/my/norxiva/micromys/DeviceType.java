package my.norxiva.micromys;

import lombok.Getter;

public enum DeviceType {
    APP("移动应用"),
    H5("H5应用"),
    WAP("WAP"),
    WEB("PC应用"),
    UNKNOWN("未知");

    @Getter
    private String name;

    DeviceType(String name) {
        this.name = name;
    }
}
