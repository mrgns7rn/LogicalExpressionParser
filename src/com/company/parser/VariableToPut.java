package com.company.parser;


public class VariableToPut {
    int num;
    boolean bol;
    boolean isnumset;

    public VariableToPut(int num) {
        this.num = num;
        this.isnumset = true;

    }

    public VariableToPut(boolean bol) {
        this.bol = bol;
        this.isnumset = false;

    }

    public boolean isNum() {
        return isnumset;
    }

    public int getNum() {
        return num;
    }

    public boolean getBol() {
        return bol;
    }
}
