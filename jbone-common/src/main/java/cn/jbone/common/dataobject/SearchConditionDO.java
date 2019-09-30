package cn.jbone.common.dataobject;

import lombok.Data;


@Data
public class SearchConditionDO {


    private Operator operator;
    private String property;
    private Object value;
    public static enum  Operator {
        EQUAL("EQ","EQ"),
        NOT_EQUAL("NEQ","NEQ"),
        LESS_THAN("LT","LT"),
        LESS_THAN_OR_EQUAL("LTOE","LTOE"),
        GREATER_THAN("GT","GT"),
        GREATER_THAN_OR_EQUAL("GTOE","GTOE"),
        LIKE("LIKE","LIKE");

        String code;
        String description;
        Operator(String code,String description){
            this.code = code;
            this.description = description;
        }
    }

    public SearchConditionDO(){}
    public SearchConditionDO(String property,Operator operator,Object value){
        this.operator = operator;
        this.property = property;
        this.value = value;
    }

    public static SearchConditionDO build(){
        return new SearchConditionDO();
    }

    public SearchConditionDO operator(Operator operator){
        this.operator = operator;
        return this;
    }
    public SearchConditionDO property(String property){
        this.property = property;
        return this;
    }
    public SearchConditionDO value(String value){
        this.value = value;
        return this;
    }
}
