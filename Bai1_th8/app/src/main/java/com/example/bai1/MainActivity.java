package com.example.bai1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    Button btnMoActivity;
    ListView lvResult;

    ArrayList<Integer> numberList;
    ArrayAdapter<Integer> adapter;


    private static final int REQUEST_CODE_INPUT = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("Ví dụ Intent Result");

        btnMoActivity = findViewById(R.id.btnMoActivity);
        lvResult = findViewById(R.id.lvResult);


        numberList = new ArrayList<>(Arrays.asList(25, 5, 36, 6, 49, 7, 81));


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numberList);
        lvResult.setAdapter(adapter);


        btnMoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputDataActivity.class);


                startActivityForResult(intent, REQUEST_CODE_INPUT);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE_INPUT) {


            if (resultCode == Activity.RESULT_OK && data != null) {


                int newNumber = data.getIntExtra("resultNumber", 0);


                numberList.add(newNumber);

                adapter.notifyDataSetChanged();
            }
        }
    }
}