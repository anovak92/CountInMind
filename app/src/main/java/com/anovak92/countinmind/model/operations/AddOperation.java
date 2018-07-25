package com.anovak92.countinmind.model.operations;

import android.graphics.Color;

import com.anovak92.countinmind.R;
import com.anovak92.countinmind.model.Operation;
import com.anovak92.countinmind.model.QuestionCase;

import java.util.Random;

public class AddOperation extends Operation {
    public AddOperation() {
        imageResId = R.drawable.add;
        labelTextColor = Color.rgb(56, 255, 23);
        label = "Adding";
    }

    @Override
    public String getOperationType() {
        return Operation.ADD;
    }

    @Override
    public QuestionCase generateQuestion() {
        Random random = new Random(System.currentTimeMillis());
        int left = random.nextInt(10);
        int right = random.nextInt(10);
        int rightIndex = random.nextInt(4);
        int rightAnswer = left + right;
        int[] answers = new int[4];
        answers[rightIndex] = rightAnswer;

        for(int tempIndex = (rightIndex + 1) % 4; tempIndex != rightIndex; tempIndex = ++tempIndex % 4 )
        {
            answers[tempIndex] = rightAnswer + (rightIndex - tempIndex);
        }

        return new QuestionCase(left,right,answers,rightIndex,Operation.ADD);
    }
}
