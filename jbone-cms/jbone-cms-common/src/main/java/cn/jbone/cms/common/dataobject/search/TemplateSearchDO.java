package cn.jbone.cms.common.dataobject.search;

import cn.jbone.common.dataobject.SearchListDO;
import lombok.Data;

@Data
public class TemplateSearchDO extends SearchListDO {

    private Long id;
    private String name;
    private String code;
    private String type;
    private String style;
    private String color;
    private int enable = -1;
}
