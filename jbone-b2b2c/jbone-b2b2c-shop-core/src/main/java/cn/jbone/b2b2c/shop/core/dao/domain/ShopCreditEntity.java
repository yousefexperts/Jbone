package cn.jbone.b2b2c.shop.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;


@Data
@Entity(name = "shop_credit")
@org.hibernate.annotations.Table(appliesTo = "shop_credit",comment = "shop_credit")
public class ShopCreditEntity {

    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "score")
    private int score;

    @Column(name = "credit_type")
    private int creditType;

    @Column(name = "credit_count")
    private int creditCount;

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

    @OneToOne
    @JoinColumn(name = "shop_id",referencedColumnName = "id",nullable = false)
    private ShopInfoEntity shopInfo;
}
