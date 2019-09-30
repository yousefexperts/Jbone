package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class SiteSettingsDO {
    private Long id;
    private Integer siteId;
    private String name;
    private String value;
    private String prompt;
    private String dataType;
    private Integer creator;
}
