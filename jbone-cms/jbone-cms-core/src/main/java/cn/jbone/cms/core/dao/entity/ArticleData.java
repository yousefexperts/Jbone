package cn.jbone.cms.core.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "article_data")
public class ArticleData extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "article_data", initialValue = 1000)
    private Long id;


    @Column(name = "content",columnDefinition="longtext")
    private String content;


    @OneToOne(targetEntity = Article.class,fetch=FetchType.LAZY)
    @JoinColumn(name = "article_id",referencedColumnName = "id")
    private Article article;


    @Column(name = "table_of_contents",columnDefinition="text")
    private String tableOfContents;

}
