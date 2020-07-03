package com.kxw.hopesfire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020-07-01 18:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseModel extends PagerModel {

    private Long id;

}
