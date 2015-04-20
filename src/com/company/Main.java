package com.company;

import com.company.parser.ExpressionTree;
import com.company.parser.VariableToPut;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter your expression>");
        String input = scn.nextLine();
        HashMap<Character, VariableToPut> variables;
        ExpressionTree exp = new ExpressionTree();
        exp.parseStringToTree(input);
        variables = exp.getAllVariables();
        askVars(variables);
        exp.setAllVariables(variables);
        System.out.println(exp.solve());

    }

    private static void askVars(HashMap<Character, VariableToPut> vars) {
        Scanner in = new Scanner(System.in);
        String inputString;
        for (Map.Entry<Character, VariableToPut> entry : vars.entrySet()) {
            System.out.println("Enter value for " + entry.getKey() + " >");
            inputString = in.nextLine();
            if (inputString.matches("[0-9]")) {
                entry.setValue(new VariableToPut(Integer.parseInt(inputString)));
            } else {
                if (inputString.equals("true")) {
                    entry.setValue(new VariableToPut(true));
                } else {
                    entry.setValue(new VariableToPut(false));
                }

            }


        }
    }
}



