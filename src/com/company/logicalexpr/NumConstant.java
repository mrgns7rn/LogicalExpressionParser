package com.company.logicalexpr;

/**
 * Created by mrgnstrn on 13.04.2015.
 */
public class NumConstant implements Atom {


    private int number = 0;

    public NumConstant(int number) {
        this.number = number;

    }

    @Override
    public int getType() {
        return NUMCONSTANTEX;
    }

    public String getStringValue() {

        return Integer.toString(number);

    }
}
