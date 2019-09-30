package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "item_category_relation")
public class ItemCategoryRelationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "item_category_id")
    private ItemCategoryEntity itemCategory;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemInfoEntity itemInfo;

    @Column(name = "operator", length = 100)
    private String operator;

    @CreationTimestamp
    @Column(name = "add_time")
    private Timestamp addTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Version
    @Column(name = "version")
    private int version;

}
