package cn.jbone.cms.common.dataobject;

import cn.jbone.cms.common.enums.BooleanEnum;
import cn.jbone.cms.common.enums.StatusEnum;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDO {

    private Long id;
    private String title;
    private String frontCover;
    private String keywords;
    private String description;
    private int hits = 0;
    private long commentCount = 0;
    private int orders = 0;
    private StatusEnum status = StatusEnum.PUBLISH;
    private BooleanEnum allowComment;
    private CategoryDO category;
    private TemplateDO template;
    private List<TagDO> tags;
    private List<Long> tagIds;
    private ArticleDataDO articleData;
    private Integer creator;
    private Long addTime;
    private Long updateTime;
    private String addTimeText;
    private String updateTimeText;
    private Integer siteId;
}
