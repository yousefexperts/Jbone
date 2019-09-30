package cn.jbone.b2b2c.decoration.core.dao.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@Table(name = "decoration_template")
public class DecorationTemplateEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;


    @Column(name = "name",nullable = false,length = 100)
    private String name;

    @Column(name = "code",nullable = false,length = 100)
    private String code;


    @Column(name = "description",length = 1000)
    private String description;


    @Column(name = "demo",length = 1000)
    private String demo;


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

}
