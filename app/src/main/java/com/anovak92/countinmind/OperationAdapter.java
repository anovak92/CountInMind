package com.anovak92.countinmind;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.anovak92.countinmind.model.Operation;

import java.util.List;

public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.MyViewHolder> {

    private List<Operation> operationsList;
    private Context mContext;
    private AppCompatActivity mActivity;
    private View clicked;

    public OperationAdapter(AppCompatActivity activity, List<Operation> operationsList){
        this.operationsList = operationsList;
        this.mContext = activity;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(mContext).inflate(R.layout.operationcard,parent,false);
        return new MyViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Operation operation = operationsList.get(position);
        holder.image.setImageDrawable(mContext.getResources().getDrawable(operation.getImageResId()));
        holder.label.setText(operation.getLabel());
        holder.label.setTextColor(operation.getLabelTextColor());

        holder.card.setOnClickListener(v->{
            clicked = holder.image;
            int[] screenLocation = new int[2];
            Intent subactivity = new Intent(mActivity,OperationActivity.class);
            v.animate().setInterpolator(new LinearInterpolator())
                    .setDuration(100).scaleY(0.8f).scaleX(0.8f).withEndAction(()->{
                    v.animate().scaleX(1).scaleY(1);
                    holder.image.getLocationOnScreen(screenLocation);
                    subactivity.
                        putExtra("left",screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width",holder.image.getWidth()).
                        putExtra("height",holder.image.getHeight()).
                        putExtra("operation",operation.getOperationType());
                    mActivity.startActivity(subactivity);
                clicked.setAlpha(0);
            });
            mActivity.overridePendingTransition(0,0);
        });
    }

    @Override
    public int getItemCount() {
        return operationsList.size();
    }

    public View getClicked() {
        return clicked;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView label;
        public CardView card;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            label = itemView.findViewById(R.id.label);
            card = itemView.findViewById(R.id.operation_card);
        }
    }

    private View.OnClickListener cardClickListener = v-> {
        v.animate()
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(200)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .withEndAction(() -> {
                    v.animate().scaleY(1.0f).scaleX(1.0f);
                });
    };

}
