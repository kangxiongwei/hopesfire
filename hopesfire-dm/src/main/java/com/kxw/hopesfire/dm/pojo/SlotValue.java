package com.kxw.hopesfire.dm.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 词槽定义
 *
 * @author kangxiongwei
 * @date 2020-12-04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotValue {

    private String value;      //词槽值
    private float score;       //词槽分数

}
