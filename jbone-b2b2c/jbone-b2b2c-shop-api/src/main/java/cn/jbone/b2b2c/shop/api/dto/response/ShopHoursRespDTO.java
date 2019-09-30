package cn.jbone.b2b2c.shop.api.dto.response;

import lombok.Data;


@Data
public class ShopHoursRespDTO {
    private String workingDayStart;

    private String workingDayEnd;

    private String weekendStart;

    private String weekendEnd;
}
