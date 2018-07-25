package com.anovak92.countinmind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.anovak92.countinmind.model.OperationFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OperationAdapter mAdapter;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mAdapter = new OperationAdapter(this, OperationFactory.crateAllAvailable());
        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        if(mAdapter.getClicked()!=null)
            mAdapter.getClicked().setAlpha(1);
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
}
