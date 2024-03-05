package com.example.helloworld;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
//import android.view.View;

import com.example.helloworld.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyViewModel model;
    private ArrayList<Integer> resultsList;
    private ArrayAdapter<Integer> arrayAdapter;
    public static final int REQUEST_CODE_DETAILS = 1001;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        resultsList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultsList);
        binding.listResults.setAdapter(arrayAdapter);


        model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getNumbers().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvCount.setText("" + integer);
                resultsList.add(integer);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        //  Cách 2: sử dụng lambda Expression
        binding.btnAdd.setOnClickListener(v -> {
            model.increaseNumber();
        });

        binding.btnSubstract.setOnClickListener(v -> {
            model.decreaseNumber();
        });

        binding.listResults.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                resultsList.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

        binding.listResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("position", position); // Truyền vị trí của mục được chọn
                intent.putExtra("number", resultsList.get(position).toString());

                //Cách mới:
                detailsActivityResultLauncher.launch(intent);

                //Cách cũ:
//                startActivityForResult(intent, REQUEST_CODE_DETAILS);
            }
        });



    }




    //Cách mới: Trong Android, khi một hoạt động con kết thúc và trả về kết quả, hoạt động cha có thể nhận kết quả này thông qua phương thức onActivityResult() hoặc một cơ chế mới hơn từ Android API level 23 trở lên là ActivityResultLauncher.
    ActivityResultLauncher<Intent> detailsActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> { //result (đối tượng ActivityResult) trong result -> {} là một Intent và nó là Intent được trả về từ DetailsActivity
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    int position = data.getIntExtra("position", -1);
                    String newData = data.getStringExtra("newData");
                    if (position != -1) {
                        resultsList.set(position, Integer.valueOf(newData));
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            });

    //Cách cũ:
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DETAILS && resultCode == RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1); // Nhận lại vị trí
            String newData = data.getStringExtra("newData"); // Nhận lại dữ liệu mới
            if (position != -1) {
                resultsList.set(position, Integer.valueOf(newData)); // Cập nhật dữ liệu tại vị trí
                arrayAdapter.notifyDataSetChanged(); // Cập nhật ListView
            }
        }
    }
    */








}