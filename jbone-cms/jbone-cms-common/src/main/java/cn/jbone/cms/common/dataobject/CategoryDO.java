package cn.jbone.cms.common.dataobject;

import cn.jbone.cms.common.enums.BooleanEnum;
import cn.jbone.cms.common.enums.CategoryShowTypeEnum;
import cn.jbone.cms.common.enums.CategoryTypeEnum;
import cn.jbone.cms.common.enums.StatusEnum;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDO {

    private Long id;
    private long pid;
    private String title;
    private String url;
    private String target;
    private int orders;
    private String frontCover;
    private List<CategoryDO> children;
    private String keywords;
    private String description;
    private CategoryTypeEnum type;
    private CategoryShowTypeEnum showType;
    private BooleanEnum inMenu;
    private StatusEnum status = StatusEnum.PUBLISH;
    private TemplateDO template;
    private List<TagDO> tags;
    private Integer siteId;
    private Integer creator;
    private String code;
}
