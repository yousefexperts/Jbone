package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "plugin",indexes = {@Index(name = "plugin_index_site_id",columnList = "site_id")})
public class Plugin extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "plugin", initialValue = 1000)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "content",columnDefinition="text")
    private String content;

    @Column(name = "enable")
    private int enable;

    @Column(name = "orders")
    private int orders;

    @Column(name = "site_id")
    private Integer siteId;
}
