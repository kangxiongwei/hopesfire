package com.kxw.hopesfire.biz.model;

import java.util.Date;

import com.kxw.hopesfire.dao.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章管理
 *
 * @author kangxiongwei
 * @date 2020/9/14 4:35 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleModel extends BaseModel {

    private String title;
    private Long iconId;
    private Long bannerId;
    private Integer articleType;
    private String keyword;
    private String summary;
    private String content;
    private Integer weight;
    private Integer status;
    private String auditReport;
    private String author;
    private String auditor;
    private Date publishTime;

    private BannerModel banner;
    private AttachModel attach;

}
