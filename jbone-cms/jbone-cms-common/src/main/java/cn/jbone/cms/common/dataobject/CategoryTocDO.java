package cn.jbone.cms.common.dataobject;


import lombok.Data;

import java.util.List;

/**
 * 专题栏目目录
 */
@Data
public class CategoryTocDO {
    private Long id;
    private long pid;
    private String title;
    private String url;
    private String target;
    private int orders;
    private String frontCover;
    private List<CategoryTocDO> children;
    private CategoryDO category;
    private ArticleDO article;
    private Long categoryId;
    private String type;
}
