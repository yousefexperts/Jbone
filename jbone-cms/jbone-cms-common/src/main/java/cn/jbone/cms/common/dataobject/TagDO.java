package cn.jbone.cms.common.dataobject;

import lombok.Data;

@Data
public class TagDO implements Comparable<TagDO>{

    private Long id;
    private String name;
    private long articleCount;
    private Integer siteId;
    private Integer creator;

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(TagDO o) {
        if(this.getArticleCount() > o.getArticleCount()){
            return -1;
        }else if(this.getArticleCount() == o.getArticleCount()){
            return 0;
        }
        return 1;
    }
}
