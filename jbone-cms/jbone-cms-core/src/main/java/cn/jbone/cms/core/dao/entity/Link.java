package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "link",indexes = {@Index(name = "link_index_site_id",columnList = "site_id")})
public class Link extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "link", initialValue = 1000)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "front_cover")
    private String frontCover;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "orders")
    private int orders;

    @Column(name = "site_id")
    private Integer siteId;
}
