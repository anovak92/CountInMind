package com.anovak92.countinmind.model;

public abstract class Operation {

    public final static String ADD = "add";
    public final static String SUB = "sub";
    public final static String MUL = "mul";
    public final static String DIV = "div";
    public final static String ALL = "all";

    protected int imageResId;
    protected int labelTextColor;
    protected String label;

    public int getImageResId(){
        return imageResId;
    }

    public int getLabelTextColor(){
        return labelTextColor;
    }

    public String getLabel() {
        return label;
    }

    public abstract String getOperationType();

    public abstract QuestionCase generateQuestion();
}
