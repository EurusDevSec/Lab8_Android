package com.example.bai1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputDataActivity extends AppCompatActivity {

    Button btnLuuBinhPhuong, btnLuuSoGoc;
    EditText edtNhapSo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_data);
        setTitle("InputDataActivity");

        btnLuuBinhPhuong = findViewById(R.id.btnLuuBinhPhuong);
        btnLuuSoGoc = findViewById(R.id.btnLuuSoGoc);
        edtNhapSo = findViewById(R.id.edtNhapSo);


        btnLuuBinhPhuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(true);
            }
        });

        btnLuuSoGoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(false);
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void sendResult(boolean isSquare){

        Intent data = new Intent();


        int number = 0;
        try {
            number = Integer.parseInt(edtNhapSo.getText().toString());
        } catch (NumberFormatException e) {

            setResult(Activity.RESULT_CANCELED);
            finish();
            return;
        }

        int resultNumber;
        if (isSquare) {
            resultNumber = number * number;
        } else {
            resultNumber = number;
        }


        data.putExtra("resultNumber", resultNumber);


        setResult(Activity.RESULT_OK, data);


        finish();
    }
}