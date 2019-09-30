package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class SiteAdminDO {
    private Long id;
    private Integer siteId;
    private Integer userId;
}
