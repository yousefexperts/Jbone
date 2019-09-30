package cn.jbone.cms.common.enums;

public enum StatusEnum {

    DELETE("DELETE"),

    PUBLISH("PUBLISH"),

    AUDIT("AUDIT");

    private String name;

    StatusEnum( String name) {
        this.name = name;
    }


}
