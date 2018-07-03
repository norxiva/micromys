package my.norxiva.micromys;

import lombok.Getter;

public enum IdType {
    ID_CARD("身份证"),
    RESIDENCE_BOOKLET("户口簿"),
    ARMY_ID_CARD("军官证"),
    SOLDIER_ID_CARD("士兵证"),
    POLICE_ID_CARD("警官证"),
    MTP_HK_MACAO("港澳居民来往内地通行证"),
    MTP_TAIWAN("台湾同胞来往内地通行证"),
    PASSPORT("护照"),
    ALIEN_RESIDENCE_PERMIT("外国人居留证"),
    ORGANIZATION_CODE("组织机构代码"),
    BUSINESS_LICENSE("营业执照"),
    OTHER_CARD("其他证件");

    @Getter
    private String name;

    IdType(String name) {
        this.name = name;
    }

}
