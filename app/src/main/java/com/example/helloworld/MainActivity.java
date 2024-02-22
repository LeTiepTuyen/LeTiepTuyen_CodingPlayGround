package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
//import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView tvCount;
    private FloatingActionButton btn_add;
    private FloatingActionButton btn_subtract;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = findViewById(R.id.tv_count);
        btn_add = findViewById(R.id.btn_add);
        btn_subtract = findViewById(R.id.btn_substract);


        // 2 dòng dưới của Cách 4:
        btn_add.setOnClickListener(new AddButtonClickListener());
        btn_subtract.setOnClickListener(new SubtractButtonClickListener());

// Cách 1: setOnclickListener trực tiếp trên button
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                ++count;
//                tvCount.setText(String.valueOf(count));
//
//            }
//        });
//
//        btn_subtract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int count = Integer.parseInt(tvCount.getText().toString());
//                --count;
//                tvCount.setText(String.valueOf(count));
//
//            }
//        });

//        });


//  Cách 2: sử dụng lambda Expression
//        btn_add.setOnClickListener(v -> {
//            int count = Integer.parseInt(tvCount.getText().toString());
//            ++count;
//            tvCount.setText(String.valueOf(count));
//        });
//
//        btn_subtract.setOnClickListener(v -> {
//            int count = Integer.parseInt(tvCount.getText().toString());
//            --count;
//            tvCount.setText(String.valueOf(count));
//        });



    }
    //Cách 3: add OnClick Listener in file XML
//    public void onAddButtonClick(View view) {
//        int count = Integer.parseInt(tvCount.getText().toString());
//        count++;
//        tvCount.setText(String.valueOf(count));
//    }
//
//    public void onSubtractButtonClick(View view) {
//        int count = Integer.parseInt(tvCount.getText().toString());
//        count--;
//        tvCount.setText(String.valueOf(count));
//    }


    //Cách 4: create separate named inner classes implements View.OnClickListener to handle the click events for the add and subtract buttons
    // Inner class for handling add button clicks
    private class AddButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int count = Integer.parseInt(tvCount.getText().toString());
            count++;
            tvCount.setText(String.valueOf(count));
        }
    }

    // Inner class for handling subtract button clicks
    private class SubtractButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int count = Integer.parseInt(tvCount.getText().toString());
            count--;
            tvCount.setText(String.valueOf(count));
        }
    }

}