package cn.jbone.b2b2c.shop.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity(name = "shop_hours")
@org.hibernate.annotations.Table(appliesTo = "shop_hours",comment = "shop_hours")
public class ShopHoursEntity {

    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "working_day_start",length = 20)
    private String workingDayStart;

    @Column(name = "working_day_end",length = 20)
    private String workingDayEnd;

    @Column(name = "weekend_start",length = 20)
    private String weekendStart;

    @Column(name = "weekend_end",length = 20)
    private String weekendEnd;

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
