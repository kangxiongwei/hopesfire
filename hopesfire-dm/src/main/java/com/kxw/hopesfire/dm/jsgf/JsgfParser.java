package com.kxw.hopesfire.dm.jsgf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.kxw.hopesfire.dm.DSTracking;
import com.kxw.hopesfire.dm.pojo.SlotValue;

import edu.cmu.sphinx.jsgf.JSGFRuleGrammar;
import edu.cmu.sphinx.jsgf.rule.JSGFRule;
import edu.cmu.sphinx.jsgf.rule.JSGFRuleAlternatives;
import edu.cmu.sphinx.jsgf.rule.JSGFRuleCount;
import edu.cmu.sphinx.jsgf.rule.JSGFRuleName;
import edu.cmu.sphinx.jsgf.rule.JSGFRuleSequence;
import edu.cmu.sphinx.jsgf.rule.JSGFRuleTag;
import edu.cmu.sphinx.jsgf.rule.JSGFRuleToken;

/**
 * 基于JSGF的自然语言理解
 *
 * @author kangxiongwei
 * @date 2021-02-19
 */
public class JsgfParser {

    /**
     * 按字进行匹配
     *
     * @param grammar   Grammar语法
     * @param rules     规则
     * @param utterance 用户说法
     * @param context   解析上下文
     * @return 匹配到的结果
     */
    public static JsgfContext match(JSGFRuleGrammar grammar, List<JSGFRule> rules, char[] utterance, JsgfContext context) {
        if (rules.isEmpty() || context.getOffset() == utterance.length) {
            return context;
        }
        for (JSGFRule rule : rules) {
            //深度优先匹配
            if (rule instanceof JSGFRuleSequence) {
                List<JSGFRule> sequenceRules = ((JSGFRuleSequence) rule).getRules();
                context = match(grammar, sequenceRules, utterance, context);
            }
            if (rule instanceof JSGFRuleAlternatives) {
                List<JSGFRule> alternativeRules = ((JSGFRuleAlternatives) rule).getRules();
                context = match(grammar, alternativeRules, utterance, context);
            }
            if (rule instanceof JSGFRuleCount) {
                JSGFRule countRules = ((JSGFRuleCount) rule).getRule();
                context = match(grammar, Lists.newArrayList(countRules), utterance, context);
            }
            if (rule instanceof JSGFRuleName) {
                String ruleName = ((JSGFRuleName) rule).getSimpleRuleName();
                JSGFRule jsgfRule = grammar.getRule(ruleName);
                context = match(grammar, Lists.newArrayList(jsgfRule), utterance, context);
            }
            if (rule instanceof JSGFRuleTag) {
                doJsgfTagRule(grammar, utterance, context, rule);
            }
            //匹配到某个字，开始从左向右进行逐字匹配
            if (rule instanceof JSGFRuleToken) {
                doJsgfTokenRule(utterance, context, rule);
            }
        }
        return context;
    }

    /**
     * 处理带标记的规则
     *
     * @param grammar   原始grammar
     * @param utterance 用户说法
     * @param context   识别上下文
     * @param rule      标记规则
     */
    private static void doJsgfTagRule(JSGFRuleGrammar grammar, char[] utterance, JsgfContext context, JSGFRule rule) {
        JSGFRule tagRule = ((JSGFRuleTag) rule).getRule();
        int startOffset = context.getOffset();
        context = match(grammar, Lists.newArrayList(tagRule), utterance, context);
        if (context.getOffset() > startOffset) {
            String tagValue = context.getUtterance().substring(startOffset, context.getOffset()).trim();
            Map<String, List<SlotValue>> slotMap = context.getSlots() == null ? new HashMap<>() : context.getSlots();
            DSTracking.setSlotValue(slotMap, ((JSGFRuleTag) rule).getTag(), tagValue);
            context.setSlots(slotMap);
        }
    }

    /**
     * 判断query从offset开始，到offset + text.length()结束的范围内，是否完全匹配
     *
     * @param utterance 用户说法
     * @param context   识别上下文
     * @param rule      字面规则
     */
    private static void doJsgfTokenRule(char[] utterance, JsgfContext context, JSGFRule rule) {
        String text = ((JSGFRuleToken) rule).getText().trim();
        int tokenOffset = 0;
        int offset = context.getOffset();
        //匹配过程中忽略空格
        while (utterance[offset] == ' ') {
            offset++;
        }
        StringBuilder builder = new StringBuilder();
        while (offset + tokenOffset < utterance.length && tokenOffset < text.length()) {
            builder.append(utterance[offset + tokenOffset]);
            tokenOffset++;
        }
        //修改偏移量
        context.setOffset(text.equals(builder.toString()) ? offset + tokenOffset : offset);
    }

}