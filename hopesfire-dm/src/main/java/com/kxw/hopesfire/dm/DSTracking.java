package com.kxw.hopesfire.dm;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.kxw.hopesfire.dm.pojo.SlotValue;

/**
 * 对话追踪
 *
 * @author kangxiongwei
 * @date 2021-02-27
 */
public class DSTracking {

    /**
     * 添加词槽
     *
     * @param slotMap   原词槽列表
     * @param slot      词槽名
     * @param slotValue 词槽值
     */
    public static void setSlotValue(Map<String, List<SlotValue>> slotMap, String slot, String slotValue) {
        SlotValue value = new SlotValue();
        value.setValue(slotValue);
        value.setScore(1.0f);
        slotMap.put(slot, Lists.newArrayList(value));
    }
}
