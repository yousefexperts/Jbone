package cn.jbone.b2b2c.shop.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity(name = "shop_gps")
@org.hibernate.annotations.Table(appliesTo = "shop_gps",comment = "shop_gps")
public class ShopGpsEntity {

    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "guid",length = 100)
    private String guid;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;

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
