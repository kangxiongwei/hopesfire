package com.kxw.hopesfire.dm.rules;

import java.util.List;
import java.util.Map;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;

import com.kxw.hopesfire.dm.pojo.SessionContext;
import com.kxw.hopesfire.dm.pojo.SlotValue;

/**
 * 规则引擎
 *
 * @author kangxiongwei
 * @date 2020-12-04
 */
public class RuleEngine {

    private static final DefaultRulesEngine RULES_ENGINE = new DefaultRulesEngine();

    /**
     * 执行规则引擎，判断下个节点
     *
     * @param edges   当前节点对应的出边
     * @param context 当前上下文
     * @return 满足条件的第一个顶点
     */
    public static String execute(List<GraphEdge> edges, SessionContext context) {
        if (edges == null || edges.isEmpty()) {
            return null;
        }
        Map<String, List<SlotValue>> slots = context.getSlots();
        Facts facts = new Facts();
        for (Map.Entry<String, List<SlotValue>> slot : slots.entrySet()) {
            facts.put(slot.getKey(), context.getTopSlotValue(slot.getKey()));
        }
        RuleAction ruleAction = new RuleAction(false);
        facts.put("action", ruleAction);
        //注册rules
        for (GraphEdge edge : edges) {
            RULES_ENGINE.fire(buildEdgeRules(edge), facts);
            if (ruleAction.isExecuteResult()) {
                return edge.getTargetId();
            }
        }
        return null;
    }

    private static Rules buildEdgeRules(GraphEdge edge) {
        Rules rules = new Rules();
        Rule rule = new MVELRule()
                .name(edge.getSourceId() + "#" + edge.getEdgeId() + "#" + edge.getTargetId())
                .priority(edge.getPriority())
                .when(edge.getCondition())
                .then("action.executeResult = true");
        rules.register(rule);
        return rules;
    }

}
