package com.company.parser;

import java.util.Stack;

/**
 * Created by mrgnstrn on 13.04.2015.
 */
public class ReversePolandNotationParser {
    private Stack<Character> operatorsStack = new Stack<Character>();


    public String parseExprToPolandNotation(String input) {

        String output = "";
        input = input.replaceAll("\\s+", "");


        char[] charArrInput = input.toCharArray();

        for (int i = 0; i <= charArrInput.length - 1; i++) {
            if (!isOperator(charArrInput[i])) {

                output += charArrInput[i];
                if (!operatorsStack.empty() && operatorsStack.peek() == '!') {
                    output += operatorsStack.pop();
                }

            } else {
                switch (charArrInput[i]) {
                    case '(':
                        operatorsStack.push(charArrInput[i]);
                        break;
                    case '!':

                        operatorsStack.push(charArrInput[i]);
                        break;
                    case '>':
                    case '<':

                        operatorsStack.push(charArrInput[i]);
                        break;
                    case '&':
                    case '|':
                        try {
                            while (!operatorsStack.empty() && (getPriority(operatorsStack.peek()) < getPriority(charArrInput[i]))) {
                                output += operatorsStack.pop();

                            }
                        } catch (notValidOperator e) {
                            System.out.println(e.getEx());

                        }
                        operatorsStack.push(charArrInput[i]);
                        break;
                    case ')': {
                        while (!operatorsStack.empty() && operatorsStack.peek() != '(') {
                            output += operatorsStack.pop();

                        }
                        if (operatorsStack.peek() == '(') {
                            operatorsStack.pop();
                        }
                    }
                    break;


                }


            }
        }
        while (!operatorsStack.empty()) {

            output += operatorsStack.pop();

        }

        return output;
    }

    private boolean isOperator(char ch) {
        if (ch == '&' ||
                ch == '|' ||
                ch == '!' ||
                ch == '<' ||
                ch == '>' ||
                ch == '(' ||
                ch == ')') return true;
        else return false;

    }

    private int getPriority(char ch) throws notValidOperator {
        switch (ch) {
            case '!':
                return 0;

            case '>':
            case '<':
                return 1;

            case '&':
            case '|':
                return 2;
            case ')':
            case '(':
                return 3;
            default:
                throw new notValidOperator(ch);


        }


    }

    class notValidOperator extends Exception {
        private final String error = "NotValidOperator: ";
        private char errSymbol;

        notValidOperator(char ch) {
            this.errSymbol = ch;

        }

        public String getEx() {
            return error + errSymbol;
        }

    }

}
