package com.anovak92.countinmind;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anovak92.countinmind.model.Operation;
import com.anovak92.countinmind.model.OperationFactory;
import com.anovak92.countinmind.presenter.QuestionCasePresenter;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OperationActivity extends AppCompatActivity {


    int top,left,width,height;
    int leftDelta,topDelta;
    float widthScale,heightScale;

    @BindView(R.id.operation_activity)RelativeLayout mainLayout;
    @BindDrawable(R.drawable.back) Drawable backgroundDrawable;
    @BindView(R.id.operation_image) ImageView operationImageView;
    @BindView(R.id.left_operand) ImageView leftOperand;
    @BindView(R.id.right_operand) ImageView rightOperand;
    @BindView(R.id.answerVariants) GridLayout answersLayout;
    @BindView(R.id.operation_background)LinearLayout operationBackground;

    private Button[] answerVariants;
    private Operation mOperation;
    private long animDuration = 1000;

    private QuestionCasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        bindAnswerButtons();
        presenter = new QuestionCasePresenter(this,leftOperand,operationImageView,rightOperand,answerVariants);
        Bundle extras = getIntent().getExtras();
        top = extras.getInt("top");
        left = extras.getInt("left");
        width = extras.getInt("width");
        height = extras.getInt("height");
        String operation = extras.getString("operation");
        mOperation = OperationFactory.create(operation);

        mainLayout.setBackground(backgroundDrawable);
        operationImageView.setImageDrawable(getResources().getDrawable(mOperation.getImageResId()));
        ViewTreeObserver observer = operationImageView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                operationImageView.getViewTreeObserver().removeOnPreDrawListener(this);

                int[] screenLocation = new int[2];
                operationImageView.getLocationOnScreen(screenLocation);
                leftDelta = left - screenLocation[0];
                topDelta = top - screenLocation[1];
                widthScale = (float) width / operationImageView.getWidth();
                heightScale = (float) height / operationImageView.getHeight();

                runEnterAnimation();
                return true;
            }
        });

    }
    private void generateQuestion(){
        presenter.setQuestionCase(mOperation.generateQuestion());
    }

    private void bindAnswerButtons(){
        int variants = answersLayout.getChildCount();
        answerVariants = new Button[variants];
        for(int childIndex = 0; childIndex < variants; childIndex++){
            answerVariants[childIndex] = (Button)answersLayout.getChildAt(childIndex);
            answerVariants[childIndex].setOnClickListener(answerClickListener);
        }
    }

    private View.OnClickListener answerClickListener = v->{
        v.animate().setInterpolator(new LinearInterpolator())
                .setDuration(100).scaleY(0.8f).scaleX(0.8f).withEndAction(()-> {
                    v.animate().scaleX(1).scaleY(1)
                    .withEndAction(this::generateQuestion);
                });
        String buttonText = ((Button) v).getText().toString();
        int answer = Integer.parseInt(buttonText.substring(1,buttonText.length()-1));
        boolean correct = presenter.checkAnswer(answer);
        String toastText = correct? "Correct" : "Incorrect";
        Toast.makeText(this, toastText ,Toast.LENGTH_SHORT).show();
    };

    private void runEnterAnimation(){

        ImageView imageView = findViewById(R.id.operation_image);

        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.setScaleX(widthScale);
        imageView.setScaleY(heightScale);
        imageView.setTranslationY(topDelta);
        imageView.setTranslationX(leftDelta);

        imageView.animate().setDuration(animDuration)
                .scaleX(1).scaleY(1)
                .translationX(0).translationY(0)
                .withEndAction(this::generateQuestion);
        AnimatorSet enterAnimatorSet = new AnimatorSet();
        enterAnimatorSet.setDuration(animDuration);
        enterAnimatorSet.playTogether(
                ObjectAnimator.ofInt(backgroundDrawable,"alpha",0,255),
                ObjectAnimator.ofInt(operationBackground.getBackground(),"alpha",0,255)
        );
        enterAnimatorSet.start();
    }

    private void runExitAnimation(){
        long duration = 1000;

        operationImageView.setPivotX(-38);//WHY
        operationImageView.setPivotY(-38);//

        operationImageView.animate().setDuration(duration)
                .scaleX(widthScale).scaleY(heightScale)
                .translationX(leftDelta).translationY(topDelta)
                .withEndAction(()->{
                   finish();
                });
        AnimatorSet exitAnimatorSet = new AnimatorSet();
        exitAnimatorSet.setDuration(animDuration);
        exitAnimatorSet.playTogether(
                ObjectAnimator.ofInt(backgroundDrawable,"alpha",255,0),
                ObjectAnimator.ofInt(operationBackground.getBackground(),"alpha",255,0)
        );
        exitAnimatorSet.start();
    }

    @Override
    public void onBackPressed() {
       presenter.clear(mOperation.getOperationType());
        runExitAnimation();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

}
