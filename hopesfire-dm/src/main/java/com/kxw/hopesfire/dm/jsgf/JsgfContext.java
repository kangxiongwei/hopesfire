package com.kxw.hopesfire.dm.jsgf;

import java.util.List;
import java.util.Map;

import com.kxw.hopesfire.dm.pojo.SlotValue;

import lombok.Data;

/**
 * JSGF转换上下文
 *
 * @author kangxiongwei
 * @date 2021-02-27
 */
@Data
public class JsgfContext {

    private String utterance;                       //用户话术
    private int offset;                             //当前偏移量
    private Map<String, List<SlotValue>> slots;     //抽取到的词槽
}
