package cn.jbone.cms.common.dataobject.search;

import cn.jbone.common.dataobject.SearchListDO;
import lombok.Data;

@Data
public class SiteSettingsSearchDO extends SearchListDO {

    private Integer siteId;
    private String name;
}
