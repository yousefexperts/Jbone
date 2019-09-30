package cn.jbone.cms.core.dao.entity;

import cn.jbone.cms.common.enums.BooleanEnum;
import cn.jbone.cms.common.enums.CategoryShowTypeEnum;
import cn.jbone.cms.common.enums.CategoryTypeEnum;
import cn.jbone.cms.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category",
        indexes = {
            @Index(name = "category_index_orders",columnList = "orders"),
            @Index(name = "category_index_site_id",columnList = "site_id"),
        },
        uniqueConstraints = {
            @UniqueConstraint(name = "category_uc_siteid_code",columnNames = {"site_id","code"})
        }
)
public class Category extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "category", initialValue = 1000)
    private Long id;


    @Column(name = "pid")
    private long pid; //默认是0


    @Column(name = "title")
    private String title;


    @Column(name = "url")
    private String url;


    @Column(name = "target")
    private String target;


    @Column(name = "orders")
    private int orders;


    @Column(name = "front_cover")
    private String frontCover;


    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "pid")
    private List<Category> childCategory;


    @Column(name = "keywords")
    private String keywords;


    @Column(name = "description")
    private String description;


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CategoryTypeEnum type;


    @Column(name = "show_type")
    @Enumerated(EnumType.STRING)
    private CategoryShowTypeEnum showType;


    @Column(name = "in_menu")
    @Enumerated(EnumType.ORDINAL)
    private BooleanEnum inMenu;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.PUBLISH;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "template_id",referencedColumnName = "id")
    private Template template;


    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "category_tag",joinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id"))
    private List<Tag> tags;


    @Column(name = "site_id")
    private Integer siteId;


    @Column(name = "code")
    private String code;
}
