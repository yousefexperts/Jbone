package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class AdvertisementDO {
    private Long id;
    private String location;
    private String type;
    private String name;
    private String content;
    private String url;
    private int enable;
    private int hits;
    private String img;
    private String text;
    private InnerDictionaryItemDO typeDetail;
    private InnerDictionaryItemDO locationDetail;
    private Integer siteId;
    private int creator;
}
