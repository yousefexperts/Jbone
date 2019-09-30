package cn.jbone.b2b2c.item.api.request;

import lombok.Data;

@Data
public class GetItemTopListReqDTO {

    private long shopId;

    private int size;

    private GetTopListSortTypeEnum sortType = GetTopListSortTypeEnum.SOLD;

    public enum GetTopListSortTypeEnum{
        STOCK,SOLD
    }
}
