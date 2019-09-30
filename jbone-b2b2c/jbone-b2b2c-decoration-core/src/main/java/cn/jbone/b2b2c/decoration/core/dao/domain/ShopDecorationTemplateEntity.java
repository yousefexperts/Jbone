package cn.jbone.b2b2c.decoration.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "shop_decoration_template")
public class ShopDecorationTemplateEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;


    @Column(name = "shop_id",nullable = false)
    private long shopId;


    @ManyToOne
    @JoinColumn(name = "template_id",nullable = false)
    private DecorationTemplateEntity decorationTemplate;


    @Column(name = "status",nullable = false)
    private int status = 1;


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
