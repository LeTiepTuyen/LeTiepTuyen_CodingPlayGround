package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.helloworld.databinding.ActivityDetailsBinding;
import com.example.helloworld.databinding.ActivityMainBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent receivedIntent = getIntent();

        if (receivedIntent != null) {
            String data = receivedIntent.getStringExtra("number");
            binding.txDetails.setText(data);
        }

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newData = binding.txDetails.getText().toString(); // Lấy dữ liệu từ EditText
                int position = receivedIntent.getIntExtra("position", -1); // Nhận lại vị trí
                if (position != -1) {
                    Intent intent = new Intent();
                    intent.putExtra("position", position);
                    intent.putExtra("newData", newData); // Gửi dữ liệu mới và vị trí quay lại MainActivity
                    setResult(RESULT_OK, intent); //thiết lập kết quả của Activity hiện tại (Activity con) trước khi nó kết thúc và trả về kết quả cho Activity gốc (Activity cha).
                    finish(); //đóng Activity hiện tại và trở về Activity gốc (Activity cha)
                }
            }
        });
    }
}