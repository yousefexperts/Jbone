package cn.jbone.common.dataobject;

import lombok.Data;


@Data
public class SearchSortDO {
    private String property;
    private Direction direction;

    public static enum Direction{
        ASC,
        DESC
    }

    public SearchSortDO(){}
    public SearchSortDO(String property,Direction direction){
        this.property = property;
        this.direction = direction;
    }

    public static SearchSortDO build(){
        return new SearchSortDO();
    }
    public SearchSortDO property(String property){
        this.property = property;
        return this;
    }
    public SearchSortDO direction(Direction direction){
        this.direction = direction;
        return this;
    }
}
