package cn.jbone.common.utils;

public enum IdTargetEnum {
    SHOP(10),
    ITEM(20),
    CMS(30),
    TAG(40),
    MAX(99);

    private int target;

    IdTargetEnum(int target){
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
