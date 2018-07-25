package com.anovak92.countinmind.model.operations;

import android.graphics.Color;

import com.anovak92.countinmind.R;
import com.anovak92.countinmind.model.Operation;
import com.anovak92.countinmind.model.QuestionCase;

import java.util.Random;

public class DivOperation extends Operation {

    public DivOperation() {
        imageResId = R.drawable.div;
        labelTextColor = Color.rgb(221, 245, 36);
        label = "Division";
    }

    @Override
    public String getOperationType() {
        return Operation.DIV;
    }

    @Override
    public QuestionCase generateQuestion() {
        Random random = new Random(System.currentTimeMillis());
        int left,right;
        do {
            left = random.nextInt(10);
            right = random.nextInt(10);
        }while (left == 0 && left % right != 0);
        int rightAnswer = left / right;

        int rightIndex = random.nextInt(4);

        int[] answers = new int[4];
        answers[rightIndex] = rightAnswer;

        for(int tempIndex = (rightIndex + 1) % 4; tempIndex != rightIndex; tempIndex = ++tempIndex % 4 )
        {
            answers[tempIndex] = rightAnswer - (rightIndex - tempIndex);
        }

        return new QuestionCase(left,right,answers,rightIndex,Operation.DIV);
    }
}
