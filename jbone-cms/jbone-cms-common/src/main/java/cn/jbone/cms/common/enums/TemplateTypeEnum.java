package cn.jbone.cms.common.enums;


public enum TemplateTypeEnum {

    NORMAL_CATEGORY("NORMAL_CATEGORY"),

    TAG_CATEGORY("TAG_CATEGORY"),

    SPECIAL_CATEGORY("SPECIAL_CATEGORY"),

    ARTICLE("ARTICLE"),

    SITE("SITE");

    private String name;

    TemplateTypeEnum(String name) {
        this.name = name;
    }
}
