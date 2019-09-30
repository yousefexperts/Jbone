package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Table(name = "item_info")
public class ItemInfoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "back_category_id",nullable = false)
    private int backCategoryId;

    @Column(name = "brand_id")
    private int brandId=0;

    @Column(name = "shop_id",nullable = false)
    private long shopId;

    @Column(name = "name",length = 200,nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private int price;

    @Column(name = "stock",nullable = false)
    private int stock;

    @Column(name = "sold",nullable = false)
    private int sold;

    @Column(name = "img_head",length = 200,nullable = false)
    private String imgHead;

    @Column(name = "imgs",length = 4096)
    private String imgs;

    @Column(name = "description")
    private String description;

    @Column(name = "status",nullable = false)
    private int status;

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

    @OneToOne(mappedBy = "itemInfo")
    private ItemDetailEntity itemDetail;

    @OneToMany(mappedBy = "itemInfo")
    private List<ItemSkuEntity> itemSkus;

    @OneToMany(mappedBy = "itemInfo")
    private List<ItemAttrEntity> itemAttrs;

    @OneToMany(mappedBy = "itemInfo")
    private List<ItemCategoryRelationEntity> itemCategoryRelations;

    @OneToMany(mappedBy = "itemInfo")
    private List<ItemTagEntity> itemTags;
}
