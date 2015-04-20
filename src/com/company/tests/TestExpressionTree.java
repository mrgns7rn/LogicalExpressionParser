package com.company.tests;

import com.company.parser.ExpressionTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mrgnstrn on 20.04.2015.
 */
public class TestExpressionTree {
    @Test
    public void testEasySolve(){
        String input;
        input = "2<3";
        ExpressionTree exp = new ExpressionTree();
        exp.parseStringToTree(input);
        Assert.assertEquals(exp.solve(),true);
    }
    @Test
    public void testHardSolve(){
        String input;
        input = "!(2>4)|(3<4)";
        ExpressionTree exp = new ExpressionTree();
        exp.parseStringToTree(input);
        Assert.assertEquals(exp.solve(),true);
    }

}
