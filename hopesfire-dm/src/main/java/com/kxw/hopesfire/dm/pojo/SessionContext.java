package com.kxw.hopesfire.dm.pojo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 对话上下文
 *
 * @author kangxiongwei
 * @date 2020-12-03
 */
@Data
public class SessionContext implements Serializable {

    private String query;                           //当前Query
    private Map<String, List<SlotValue>> slots;     //对话上下文已有词槽

    /**
     * 获取词槽的最优解
     *
     * @param slotName 词槽名称
     */
    public String getTopSlotValue(String slotName) {
        if (this.slots == null || this.slots.isEmpty() || !this.slots.containsKey(slotName)) {
            return null;
        }
        List<SlotValue> slotValues = this.slots.get(slotName);
        if (slotValues == null || slotValues.isEmpty()) {
            return null;
        } else if (slotValues.size() == 1) {
            return slotValues.get(0).getValue();
        } else {
            return slotValues.stream().max(Comparator.comparing(SlotValue::getScore)).get().getValue();
        }
    }

}
