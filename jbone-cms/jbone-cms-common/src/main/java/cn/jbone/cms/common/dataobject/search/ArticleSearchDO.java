package cn.jbone.cms.common.dataobject.search;

import cn.jbone.cms.common.dataobject.config.ArticleFiledConfigDO;
import cn.jbone.cms.common.enums.StatusEnum;
import cn.jbone.common.dataobject.SearchListDO;
import cn.jbone.common.dataobject.SearchSortDO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleSearchDO extends SearchListDO {
    private Long id;
    private String title;
    private String keywords;
    private String description;
    private List<StatusEnum> statusList = new ArrayList<>();
    private Long categoryId;
    private List<Long> categoryIds;
    private Long templateId;
    private List<Long> tagIds;
    private Integer creator;
    private Integer siteId;
    public static int DEFAULT_SIZE = 10;

    public static ArticleSearchDO build(){
        return build(1,DEFAULT_SIZE);
    }

    public static ArticleSearchDO build(int pageNumber){
        return build(pageNumber,DEFAULT_SIZE);
    }

    public static ArticleSearchDO build(int pageNumber, int pageSize){
        ArticleSearchDO articleSearchDO = new ArticleSearchDO();
        articleSearchDO.setPageNumber(pageNumber);
        articleSearchDO.setPageSize(pageSize);
        return articleSearchDO;
    }

    public ArticleSearchDO id(Long id){
        this.id = id;
        return this;
    }

    public ArticleSearchDO title(String title){
        this.title = title;
        return this;
    }

    public ArticleSearchDO keywords(String keywords){
        this.keywords = keywords;
        return this;
    }

    public ArticleSearchDO description(String description){
        this.description = description;
        return this;
    }

    public ArticleSearchDO statusList(List<StatusEnum> statusList){
        this.statusList = statusList;
        return this;
    }

    public ArticleSearchDO categoryId(Long categoryId){
        this.categoryId = categoryId;
        return this;
    }

    public ArticleSearchDO templateId(Long templateId){
        this.templateId = templateId;
        return this;
    }

    public ArticleSearchDO tagIds(List<Long> tagIds){
        this.tagIds = tagIds;
        return this;
    }

    public ArticleSearchDO creator(Integer creator){
        this.creator = creator;
        return this;
    }

    public ArticleSearchDO pageSize(int pageSize){
        super.setPageSize(pageSize);
        return this;
    }

    public ArticleSearchDO pageNumber(int pageNumber){
        super.setPageNumber(pageNumber);
        return this;
    }

    private ArticleFiledConfigDO config = new ArticleFiledConfigDO();
}
