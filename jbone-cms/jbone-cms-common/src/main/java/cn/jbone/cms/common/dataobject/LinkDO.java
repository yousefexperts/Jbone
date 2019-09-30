package cn.jbone.cms.common.dataobject;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LinkDO {

    private Long id;
    private String title;
    private String frontCover;
    private String url;
    private String description;
    private int orders;
    private Timestamp addTime;
    private Timestamp updateTime;
    private Integer creator;
    private Integer siteId;
}
