package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章栏目
 *
 * @author kangxiongwei
 * @date 2020/9/14 5:19 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_banner")
public class BannerEntity extends BaseEntity {

    private String banner;
    private Integer bannerType;
    private Long parentId;
    private Integer weight;
    private Integer status;
    private String username;

}
