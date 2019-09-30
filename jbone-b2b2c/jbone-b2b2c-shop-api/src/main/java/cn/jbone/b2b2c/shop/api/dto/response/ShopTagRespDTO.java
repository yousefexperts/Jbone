package cn.jbone.b2b2c.shop.api.dto.response;

import lombok.Data;


@Data
public class ShopTagRespDTO {

    private long tagId;
    private long shopId;
    private String tagName;
}
