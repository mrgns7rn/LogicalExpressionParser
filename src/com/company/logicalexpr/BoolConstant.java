package com.company.logicalexpr;

/**
 * Created by mrgnstrn on 13.04.2015.
 */
public class BoolConstant implements Atom {
    private boolean bol = false;

    public BoolConstant(boolean bol) {
        this.bol = bol;
    }

    @Override
    public int getType() {
        return BOLCONSTANTEX;
    }

    @Override
    public String getStringValue() {
        return Boolean.toString(bol);
    }
}
