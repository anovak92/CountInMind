package com.anovak92.countinmind.model.operations;

import android.graphics.Color;

import com.anovak92.countinmind.R;
import com.anovak92.countinmind.model.Operation;
import com.anovak92.countinmind.model.OperationFactory;
import com.anovak92.countinmind.model.QuestionCase;

import java.util.Random;

public class AllOperation extends Operation {

    public AllOperation() {
        imageResId = R.drawable.all;
        labelTextColor = Color.rgb(178, 0, 170);
        label = "All";
    }

    @Override
    public String getOperationType() {
        return Operation.ALL;
    }

    @Override
    public QuestionCase generateQuestion() {
        Random random = new Random(System.currentTimeMillis());
        String[] operations = new String[]{Operation.ADD,Operation.SUB,Operation.MUL,Operation.DIV};
        int randomIndex = random.nextInt(4);

        return OperationFactory.create(operations[randomIndex]).generateQuestion();
    }
}
