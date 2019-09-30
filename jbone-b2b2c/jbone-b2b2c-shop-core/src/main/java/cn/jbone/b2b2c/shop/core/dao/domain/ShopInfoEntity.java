package cn.jbone.b2b2c.shop.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity(name = "shop_info")
@org.hibernate.annotations.Table(appliesTo = "shop_info",comment = "shop_info")
public class ShopInfoEntity {
    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "shop_name",length = 100,nullable = false)
    private String shopName;

    @Column(name = "shop_logo",length = 200,nullable = true)
    private String shopLogo;

    @Column(name = "qrcode",length = 200,nullable = true)
    private String qrcode;

    @Column(name = "front_cover",length = 200,nullable = true)
    private String frontCover;

    @Column(name = "note",length = 1000,nullable = true)
    private String note;

    @Column(name = "status",columnDefinition = "int default 1 not null")
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

    @OneToOne(mappedBy = "shopInfo")
    private ShopGpsEntity shopGps;

    @OneToOne(mappedBy = "shopInfo")
    private ShopHoursEntity shopHours;

    @OneToOne(mappedBy = "shopInfo")
    private ShopCreditEntity shopCredit;

    @OneToMany(mappedBy = "shopInfo")
    private List<ShopTagEntity> shopTags;
}
