package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "item_attr")
public class ItemAttrEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_sku_id",nullable = false)
    private long itemSkuId=0;

    @Column(name = "property_id",nullable = false)
    private int propertyId;

    @Column(name = "value_id",nullable = false)
    private int valueId=0;

    @Column(name = "value_content",length = 1000)
    private String valueContent;

    @Column(name = "is_sku",nullable = false)
    private int isSku=0;

    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
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
