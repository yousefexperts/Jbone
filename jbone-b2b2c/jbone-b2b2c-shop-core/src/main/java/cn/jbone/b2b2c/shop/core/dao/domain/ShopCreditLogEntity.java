package cn.jbone.b2b2c.shop.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity(name = "shop_credit_log")
@org.hibernate.annotations.Table(appliesTo = "shop_credit_log",comment = "shop_credit_log")
public class ShopCreditLogEntity {

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

    @Column(name = "increment_score")
    private int incrementScore;

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

    @ManyToOne
    @JoinColumn(name = "shop_id",referencedColumnName = "id",nullable = false)
    private ShopInfoEntity shopInfo;
}
