package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "dictionary_group")
public class DictionaryGroup extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "dictionary_group", initialValue = 1000)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "group")
    private List<DictionaryItem> items;

}
