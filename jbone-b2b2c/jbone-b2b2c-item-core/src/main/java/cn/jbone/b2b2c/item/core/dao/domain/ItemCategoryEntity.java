package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Table(name = "item_category")
public class ItemCategoryEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "pid",nullable = false)
    private long pid = 0;

    @Column(name = "shop_id",nullable = false)
    private long shopId;

    @Column(name = "status",nullable = false)
    private int status;

    @Column(name = "sort_num",nullable = false)
    private int sortNum;

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

    @OneToMany(mappedBy = "itemCategory")
    private List<ItemCategoryRelationEntity> itemCategoryRelations;

}
