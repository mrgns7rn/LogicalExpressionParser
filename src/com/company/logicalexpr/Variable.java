package com.company.logicalexpr;

/**
 * Created by mrgnstrn on 13.04.2015.
 */
public class Variable implements Atom {
    private char varName;

    public Variable(char varName) {
        this.varName = varName;


    }

    @Override
    public int getType() {
        return VARIABLE;
    }

    @Override
    public String getStringValue() {
        return String.valueOf(varName);
    }
}