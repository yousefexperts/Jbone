package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "advertisement",indexes = {@Index(name = "advertisement_index_site_id",columnList = "site_id")})
public class Advertisement extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "advertisement", initialValue = 1000)
    private Long id;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "content",length = 1024)
    private String content;

    @Column(name = "img",length = 1024)
    private String img;

    @Column(name = "text",length = 1024)
    private String text;

    @Column(name = "url")
    private String url;

    @Column(name = "enable")
    private int enable;

    @Column(name = "hits")
    private int hits;

    @Column(name = "site_id")
    private Integer siteId;
}
