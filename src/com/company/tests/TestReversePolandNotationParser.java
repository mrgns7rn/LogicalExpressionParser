package com.company.tests;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mrgnstrn on 14.04.2015.
 */
import com.company.parser.ReversePolandNotationParser;
public class TestReversePolandNotationParser {
    @Test
    public void testParseExprToPolandNotationSimple(){
        String input;
        String output;
        String expected;
        ReversePolandNotationParser prs = new ReversePolandNotationParser();
        input = "a<3";
        expected = "a3<";
        output = prs.parseExprToPolandNotation(input);
        Assert.assertEquals(output,expected);
    }
    @Test
    public void testParseExprToPolandNotationHard(){
        String input;
        String output;
        String expected;
        ReversePolandNotationParser prs = new ReversePolandNotationParser();
        input = "(a<3)|(4>d)";
        expected = "a3<4d>|";
        output = prs.parseExprToPolandNotation(input);
        Assert.assertEquals(output,expected);

    }
    @Test
    public void testParseExprToPolandNotationHardest(){
        String input;
        String output;
        String expected;
        ReversePolandNotationParser prs = new ReversePolandNotationParser();
        input = "((!b)|(3<b))&((c>5)&((!g)|(b>1)))";
        expected = "b!3b<|c5>g!b1>|&&";
        output = prs.parseExprToPolandNotation(input);
        Assert.assertEquals(output,expected);

    }
}
