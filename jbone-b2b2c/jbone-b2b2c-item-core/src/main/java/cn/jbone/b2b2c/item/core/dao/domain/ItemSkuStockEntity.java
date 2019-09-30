package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Table(name = "item_sku_stock")
public class ItemSkuStockEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_id",nullable = false)
    private long itemId;

    @Column(name = "shop_id",nullable = false)
    private long shopId;

    @Column(name = "price",nullable = false)
    private int price;

    @Column(name = "stock",nullable = false)
    private int stock;

    @Column(name = "sold",nullable = false)
    private int sold;

    @Column(name = "status")
    private int status=1;

    @OneToOne
    @JoinColumn(name = "item_sku_id")
    private ItemSkuEntity itemSku;

    @OneToMany(mappedBy = "itemSkuStock")
    private List<ItemSkuStockStatusEntity> itemSkuStockStatuses;

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
