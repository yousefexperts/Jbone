package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "site",uniqueConstraints = {@UniqueConstraint(columnNames="domain"),@UniqueConstraint(columnNames="alias1"),@UniqueConstraint(columnNames="alias2")})
public class Site extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "site", initialValue = 1000)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "domain")
    private String domain;

    @Column(name = "alias1")
    private String alias1;

    @Column(name = "alias2")
    private String alias2;

    @Column(name = "pid")
    private int pid;

    @Column(name = "orders")
    private int orders;

    @Column(name = "template_id")
    private long templateId;

    @Column(name = "enable")
    private int enable;
}
