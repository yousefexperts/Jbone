package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 站点管理员
 */
@Data
@Entity
@Table(name = "site_admin",
        indexes = {@Index(name = "site_admin_index_siteid",columnList = "site_id"),
                @Index(name = "site_admin_index_userid",columnList = "user_id")
})
public class SiteAdmin extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "site_settings", initialValue = 1000)
    private Long id;

    @Column(name = "site_id")
    private Integer siteId;

    @Column(name = "user_id")
    private Integer userId;
}
