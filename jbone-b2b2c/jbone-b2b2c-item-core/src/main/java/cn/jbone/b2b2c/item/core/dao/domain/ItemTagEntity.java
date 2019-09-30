package cn.jbone.b2b2c.item.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "item_tag")
public class ItemTagEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tag_id",nullable = false)
    private int tagId;

    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private ItemInfoEntity itemInfo;

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
