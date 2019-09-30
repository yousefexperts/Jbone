package cn.jbone.b2b2c.shop.api.dto.response;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class ShopInfoRespDTO {
    private long id;

    private String shopName;

    private String shopLogo;

    private String qrcode;

    private String frontCover;

    private String note;

    private int status;

    private String operator;

    private Timestamp addTime;

    private Timestamp updateTime;
}
