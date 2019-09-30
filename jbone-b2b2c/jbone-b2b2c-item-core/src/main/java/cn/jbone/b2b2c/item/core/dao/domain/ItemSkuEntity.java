package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "item_sku")
public class ItemSkuEntity {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title",length = 200,nullable = false)
    private String title;

    @Column(name = "property_values",length = 200)
    private String propertyValues;

    @Column(name = "img",length = 200,nullable = false)
    private String img;

    @Column(name = "status",nullable = false)
    private int status=1;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemInfoEntity itemInfo;

    @OneToOne(mappedBy = "itemSku")
    private ItemSkuStockEntity itemSkuStock;

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
