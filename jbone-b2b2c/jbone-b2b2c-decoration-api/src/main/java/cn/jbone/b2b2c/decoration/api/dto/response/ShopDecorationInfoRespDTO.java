package cn.jbone.b2b2c.decoration.api.dto.response;

import lombok.Data;

@Data
public class ShopDecorationInfoRespDTO {
    private long templateId;
    private String templateCode;
    private String templateName;
    private String templateDescription;
    private int status;
}
