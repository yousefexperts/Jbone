package cn.jbone.cms.core.dao.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dictionary_item")
public class DictionaryItem extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "dictionary_group", initialValue = 1000)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "group_id",referencedColumnName = "id")
    @NotFound(action= NotFoundAction.IGNORE)
    private DictionaryGroup group;

    @Column(name = "dict_name")
    private String dictName;

    @Column(name = "dict_value")
    private String dictValue;

    @Column(name = "dict_prompt")
    private String dictPrompt;

    @Column(name = "orders")
    private int orders;

}
