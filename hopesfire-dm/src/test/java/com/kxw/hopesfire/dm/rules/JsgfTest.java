package com.kxw.hopesfire.dm.rules;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.kxw.hopesfire.dm.jsgf.JsgfContext;
import com.kxw.hopesfire.dm.jsgf.JsgfParser;

import edu.cmu.sphinx.jsgf.JSGFRuleGrammar;
import edu.cmu.sphinx.jsgf.JSGFRuleGrammarFactory;
import edu.cmu.sphinx.jsgf.JSGFRuleGrammarManager;
import edu.cmu.sphinx.jsgf.parser.JSGFParser;
import edu.cmu.sphinx.jsgf.rule.JSGFRule;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.props.ConfigurationManager;

/**
 * @author kangxiongwei <kangxiongwei@kuaishou.com>
 * Created on 2021-01-16
 */
public class JsgfTest {

    @Test
    public void testJsgf() throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("hello.gram");
        assert is != null;
        JSGFRuleGrammarFactory factory = new JSGFRuleGrammarFactory(new JSGFRuleGrammarManager());
        JSGFParser parser = new JSGFParser(is);
        JSGFRuleGrammar grammar = parser.GrammarUnit(factory);
        System.out.println(grammar);

        String utterance = "今天北京天气咋样";
        JsgfContext context = new JsgfContext();
        context.setUtterance(utterance);
        for (String ruleName : grammar.getRuleNames()) {
            JSGFRule rule = grammar.getRule(ruleName);
            JsgfParser.match(grammar, Lists.newArrayList(rule), utterance.toCharArray(), context);
            if (context.getOffset() == utterance.length()) {
                //说明识别成功了
                System.out.println("ruleName " + ruleName + " result is:");
                System.out.println(context);
                break;
            }
        }

        is.close();
    }

    @Test
    public void test02() throws Exception {
        URL resource = this.getClass().getClassLoader().getResource("hello.config.xml");
        ConfigurationManager cm = new ConfigurationManager(resource);
        Recognizer recognizer = cm.lookup("recognizer");
        recognizer.allocate();


    }

}
