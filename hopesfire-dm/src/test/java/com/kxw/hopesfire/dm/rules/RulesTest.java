package com.kxw.hopesfire.dm.rules;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;
import org.junit.Test;

import com.kxw.hopesfire.dm.pojo.SessionContext;
import com.kxw.hopesfire.dm.pojo.SlotValue;

/**
 * @author kangxiongwei
 * Created on 2020-12-03
 */
public class RulesTest {

    @Test
    public void testJson() throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new JsonRuleDefinitionReader());
        String file = RulesTest.class.getClassLoader().getResource("rules.json").getFile();
        Rules rules = ruleFactory.createRules(new FileReader(file));

        DefaultRulesEngine rulesEngine = new DefaultRulesEngine();
        Facts facts = new Facts();
        facts.put("username", "kangxiongwei");
        facts.put("age", 16);
        rulesEngine.fire(rules, facts);
    }

    @Test
    public void testRuleEngine() {
        SessionContext context = new SessionContext();
        Map<String, List<SlotValue>> slots = new HashMap<>();
        slots.put("name", Collections.singletonList(new SlotValue("xxx", 0.9f)));
        slots.put("age", Collections.singletonList(new SlotValue("10", 0.99f)));
        context.setSlots(slots);

        List<GraphEdge> edges = new ArrayList<>();
        edges.add(GraphEdge.builder().edgeId("edgeId_01").sourceId("100").targetId("101").condition("name == 'xxx' && age > 10").priority(50).build());
        edges.add(GraphEdge.builder().edgeId("edgeId_02").sourceId("100").targetId("102").condition("name == 'xxx' && age < 10").priority(50).build());

        String execute = RuleEngine.execute(edges, context);
        System.out.println(execute);
    }

}
