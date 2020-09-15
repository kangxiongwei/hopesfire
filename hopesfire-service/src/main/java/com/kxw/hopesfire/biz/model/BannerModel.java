package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 栏目管理
 *
 * @author kangxiongwei
 * @date 2020/9/14 4:41 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BannerModel extends BaseModel {

    private String banner;
    private Integer bannerType;
    private BannerModel parent;
    private Integer weight;
    private Integer status;
    private String username;

    public BannerModel(Long id) {
        this.setId(id);
    }

}
