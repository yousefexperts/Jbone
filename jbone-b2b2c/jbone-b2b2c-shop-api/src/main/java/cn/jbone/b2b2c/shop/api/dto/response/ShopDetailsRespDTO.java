package cn.jbone.b2b2c.shop.api.dto.response;

import lombok.Data;

import java.util.List;


@Data
public class ShopDetailsRespDTO {

    private ShopInfoRespDTO shopInfo;

    private ShopCreditRespDTO shopCredit;

    private ShopHoursRespDTO shopHours;

    private List<ShopTagRespDTO> shopTags;

    private ShopGpsRespDTO shopGps;
}
