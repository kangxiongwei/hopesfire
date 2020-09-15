package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 文章管理
 *
 * @author kangxiongwei
 * @date 2020/9/14 4:35 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_article")
public class ArticleEntity extends BaseEntity {

    private String title;
    private Long iconId;
    private Long bannerId;
    private String keyword;
    private String summary;
    private String content;
    private Integer weight;
    private Integer status;
    private String auditReport;
    private String author;
    private String auditor;
    private Date publishTime;

}
