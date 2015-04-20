package com.company.logicalexpr;

/**
 * Created by mrgnstrn on 13.04.2015.
 */
public class Operator implements Atom {
    private final static int OR = 0;
    private final static int AND = 1;
    private final static int NOT = 2;
    private final static int LESSTHAN = 3;
    private final static int MORETHAN = 4;
    private int operatorType;

    public Operator(char op) {
        switch (op) {
            case '|':
                this.operatorType = 0;
                break;
            case '&':
                this.operatorType = 1;
                break;
            case '!':
                this.operatorType = 2;
                break;
            case '<':
                this.operatorType = 3;
                break;
            case '>':
                this.operatorType = 4;
                break;

        }
    }


    @Override
    public int getType() {
        return OPERATOR;
    }

    @Override
    public String getStringValue() {
               switch (operatorType) {
            case 0:
                return "OR";

            case 1:
                return "AND";


            case 2:
                return "NOT";


            case 3:
                return "<";


            case 4:
                return ">";

            default:
                return null;


        }

    }
    public int getOperatorType() {
       return operatorType;

    }
}