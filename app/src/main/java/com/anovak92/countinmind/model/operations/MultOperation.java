package com.anovak92.countinmind.model.operations;

import android.graphics.Color;

import com.anovak92.countinmind.R;
import com.anovak92.countinmind.model.Operation;
import com.anovak92.countinmind.model.QuestionCase;

import java.util.Random;

public class MultOperation extends Operation {

    public MultOperation() {
        imageResId = R.drawable.mul;
        labelTextColor = Color.rgb(42, 36, 156);
        label = "Multiplexing";
    }

    @Override
    public String getOperationType() {
        return Operation.MUL;
    }

    @Override
    public QuestionCase generateQuestion() {
        Random random = new Random(System.currentTimeMillis());
        int left = random.nextInt(10);
        int right = random.nextInt(10);

        int rightIndex = random.nextInt(4);
        int rightAnswer = left * right;
        int[] answers = new int[4];
        answers[rightIndex] = rightAnswer;

        for(int tempIndex = (rightIndex + 1) % 4; tempIndex != rightIndex; tempIndex = ++tempIndex % 4 )
        {
            answers[tempIndex] = rightAnswer + (rightIndex - tempIndex);
        }

        return new QuestionCase(left,right,answers,rightIndex,Operation.MUL);
    }
}
