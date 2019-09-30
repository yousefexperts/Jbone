package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "item_sku_stock_status")
public class ItemSkuStockStatusEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id",nullable = false)
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "item_stock_id",nullable = false)
    private ItemSkuStockEntity itemSkuStock;

    @Column(name = "stock_status",nullable = false)
    private int stockStatus;

    @Column(name = "sold_status",nullable = false)
    private int soldStatus;

    @Column(name = "num",nullable = false)
    private int num;

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
