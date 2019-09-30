package cn.jbone.b2b2c.shop.api.dto.response;

import lombok.Data;


@Data
public class ShopCreditRespDTO {
    private int score;
    private int creditType;
    private int creditCount;
}
