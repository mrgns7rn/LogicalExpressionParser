package com.company.logicalexpr;

/**
 * Created by mrgnstrn on 13.04.2015.
 */
public interface Atom {
    final static int NUMCONSTANTEX = 1;
    final static int BOLCONSTANTEX = 2;
    final static int OPERATOR = 3;
    final static int VARIABLE = 4;

    int getType();

    String getStringValue();

}

