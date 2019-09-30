package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class SiteDO {
    private Integer id;
    private String name;
    private String description;
    private String domain;
    private String alias1;
    private String alias2;
    private int pid;
    private int orders;
    private long templateId;
    private int enable;
    private TemplateDO template;
}
