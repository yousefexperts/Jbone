package cn.jbone.cms.core.dao.entity;

import cn.jbone.cms.common.enums.BooleanEnum;
import cn.jbone.cms.common.enums.StatusEnum;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "article",
        indexes = {@Index(name = "article_index_orders",columnList = "orders"),
                @Index(name = "article_index_hits",columnList = "hits"),
                @Index(name = "article_index_add_time",columnList = "add_time"),
                @Index(name = "article_index_site_id",columnList = "site_id")
        })
public class Article extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "article", initialValue = 1000)
    private Long id;


    @Column(name = "title")
    private String title;


    @Column(name = "front_cover",columnDefinition="text")
    private String frontCover;


    @Column(name = "keywords")
    private String keywords;


    @Column(name = "description")
    private String description;


    @Column(name = "hits")
    private int hits;


    @Column(name = "orders")
    private int orders;


    @Column(name = "site_id")
    private Integer siteId;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.PUBLISH;


    @Column(name = "allow_comment")
    @Enumerated(EnumType.ORDINAL)
    private BooleanEnum allowComment;


    @OneToOne(targetEntity = ArticleData.class,mappedBy = "article",fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
    @NotFound(action= NotFoundAction.IGNORE)
    private ArticleData articleData;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @NotFound(action= NotFoundAction.IGNORE)
    private Category category;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "template_id",referencedColumnName = "id")
    @NotFound(action= NotFoundAction.IGNORE)
    private Template template;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "article_tag",joinColumns = @JoinColumn(name = "article_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id"))
    private List<Tag> tags;

}
