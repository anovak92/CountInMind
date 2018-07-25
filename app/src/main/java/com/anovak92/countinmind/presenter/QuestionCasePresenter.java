package com.anovak92.countinmind.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.anovak92.countinmind.R;
import com.anovak92.countinmind.model.Operation;
import com.anovak92.countinmind.model.QuestionCase;

public class QuestionCasePresenter {

    private Context context;

    private ImageView leftOperand;
    private ImageView operation;
    private ImageView rightOperand;
    private Button[] answerVariants;

    private QuestionCase currentQuestionCase;

    public QuestionCasePresenter(Context context, ImageView leftOperand, ImageView operation, ImageView rightOperand, Button[] answerVariants) {
        this.context = context;
        this.leftOperand = leftOperand;
        this.operation = operation;
        this.rightOperand = rightOperand;
        this.answerVariants = answerVariants;
    }

    public void setQuestionCase(QuestionCase questionCase){
        currentQuestionCase = questionCase;
        leftOperand.setImageDrawable(operandAdapter(questionCase.getLeft()));
        operation.setImageDrawable(operationAdapter(questionCase.getOperationType()));
        rightOperand.setImageDrawable(operandAdapter(questionCase.getRight()));
        int[] variants = questionCase.getVariants();
        for(int index = 0; index < variants.length; index++){
            answerVariants[index].setText("["+variants[index]+"]");
            answerVariants[index].setVisibility(View.VISIBLE);
        }
    }

    public boolean checkAnswer(int answer){
        return currentQuestionCase.getRightAnswer() == answer;
    }

    public void clear(String operationType){
        currentQuestionCase = null;
        leftOperand.setImageResource(android.R.color.transparent);
        rightOperand.setImageResource(android.R.color.transparent);
        operation.setImageDrawable(operationAdapter(operationType));
        for(Button answerButton: answerVariants)
            answerButton.setVisibility(View.INVISIBLE);
    }

    private Drawable operandAdapter(int operandValue){
        switch (operandValue){
            case 0:
                return context.getResources().getDrawable(R.drawable.zero);
            case 1:
                return context.getResources().getDrawable(R.drawable.one);
            case 2:
                return context.getResources().getDrawable(R.drawable.two);
            case 3:
                return context.getResources().getDrawable(R.drawable.three);
            case 4:
                return context.getResources().getDrawable(R.drawable.four);
            case 5:
                return context.getResources().getDrawable(R.drawable.five);
            case 6:
                return context.getResources().getDrawable(R.drawable.six);
            case 7:
                return context.getResources().getDrawable(R.drawable.seven);
            case 8:
                return context.getResources().getDrawable(R.drawable.eight);
            case 9:
                return context.getResources().getDrawable(R.drawable.nine);
                default:
                    return context.getResources().getDrawable(R.drawable.sub);
        }
    }

    private Drawable operationAdapter(String operation){
        switch (operation){
            case Operation.ADD:
                return context.getResources().getDrawable(R.drawable.add);
            case Operation.SUB:
                return context.getResources().getDrawable(R.drawable.sub);
            case Operation.DIV:
                return context.getResources().getDrawable(R.drawable.div);
            case Operation.MUL:
                return context.getResources().getDrawable(R.drawable.mul);
                default:
                    return context.getResources().getDrawable(R.drawable.all);

        }
    }


}
