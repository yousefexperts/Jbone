package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "settings",uniqueConstraints = {@UniqueConstraint(columnNames="setting_key")})
public class Settings extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "settings", initialValue = 1000)
    private Long id;

    @Column(name = "setting_key")
    private String settingKey;

    @Column(name = "setting_value")
    private String settingValue;

    @Column(name = "description")
    private String description;
}
