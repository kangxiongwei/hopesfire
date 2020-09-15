package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
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
public class ArticleModel extends BaseModel {

    private String title;
    private AttachModel icon;
    private BannerModel banner;
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
