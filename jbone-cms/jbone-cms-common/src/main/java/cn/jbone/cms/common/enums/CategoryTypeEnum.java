package cn.jbone.cms.common.enums;


public enum  CategoryTypeEnum {

    CATEGORY("CATEGORY"),

    TAG("TAG"),

    IMG("IMG"),

    NEWS("NEWS"),

    PRODUCT("PRODUCT"),

    SPECIAL("SPECIAL");

    private String name;

    CategoryTypeEnum(String name) {
        this.name = name;
    }
}
