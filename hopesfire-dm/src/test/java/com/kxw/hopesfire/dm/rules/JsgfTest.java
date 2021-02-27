package com.kxw.hopesfire.dm.rules;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

import edu.cmu.sphinx.jsgf.JSGFRuleGrammar;
import edu.cmu.sphinx.jsgf.JSGFRuleGrammarFactory;
import edu.cmu.sphinx.jsgf.JSGFRuleGrammarManager;
import edu.cmu.sphinx.jsgf.parser.JSGFParser;
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
