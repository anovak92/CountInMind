package com.anovak92.countinmind.model;

public class QuestionCase {
    private int left;
    private int right;
    private int[] variants;
    private int rightAnswerIndex;
    private String operationType;

    public QuestionCase(int left, int right, int[] variants, int rightAnswerIndex,String operationType) {
        this.left = left;
        this.right = right;
        this.variants = variants;
        this.rightAnswerIndex = rightAnswerIndex;
        this.operationType = operationType;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int[] getVariants() {
        return variants;
    }

    public int getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public int getRightAnswer(){
        return variants[rightAnswerIndex];
    }

    public String getOperationType() {
        return operationType;
    }
}
