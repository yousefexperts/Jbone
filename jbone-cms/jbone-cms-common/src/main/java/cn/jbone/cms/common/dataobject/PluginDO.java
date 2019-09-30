package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class PluginDO {

    private int id;
    private String name;
    private String type;
    private String content;
    private int enable;
    private int orders;
    private Integer siteId;
    private InnerDictionaryItemDO pluginType;
    private Integer creator;
}
