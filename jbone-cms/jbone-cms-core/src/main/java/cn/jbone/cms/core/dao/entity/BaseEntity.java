package cn.jbone.cms.core.dao.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {


    @CreationTimestamp
    @Basic
    @Column(name = "add_time")
    private Timestamp addTime;


    @UpdateTimestamp
    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;


    @Version
    @Column(name = "version")
    private int version;


    @Column(name = "creator")
    private Integer creator;
}
