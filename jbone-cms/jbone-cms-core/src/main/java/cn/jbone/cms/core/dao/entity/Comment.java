package cn.jbone.cms.core.dao.entity;

import cn.jbone.cms.common.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "comment", initialValue = 1000)
    private Long id;

    @Column(name = "content",columnDefinition="text")
    private String content;

    @Column(name = "ip")
    private String ip;

    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "username")
    private String username;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "site_id")
    private Integer siteId;
}
