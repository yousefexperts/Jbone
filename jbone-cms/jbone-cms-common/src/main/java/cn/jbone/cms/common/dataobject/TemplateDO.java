package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class TemplateDO {

    private Long id;
    private String name;
    private String code;
    private String frontCover;
    private String images;
    private String description;
    private String type;
    private String style;
    private String color;
    private int enable;
    private Integer creator;
    private int singlePage;
    private String dataCollectors;
}
