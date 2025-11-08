package com.example.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    private TextView tvDaySo;
    private Button btnNhapDaySo, btnSapXeSo, btnTimKiem1So, btnDong;


    private final ActivityResultLauncher<Intent> inputLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String daySo = result.getData().getStringExtra("dayso");
                    tvDaySo.setText(daySo);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setTitle("Màn hình 4 chức năng");
        btnNhapDaySo = findViewById(R.id.btnNhapDaySo);
        btnSapXeSo = findViewById(R.id.btnSapXeSo);
        btnTimKiem1So = findViewById(R.id.btnTimKiem1So);
        btnDong = findViewById(R.id.btnDong);
        tvDaySo = findViewById(R.id.tvDaySo);


        btnNhapDaySo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            inputLauncher.launch(intent);
        });


        btnSapXeSo.setOnClickListener(v -> {
            String currentSequence = tvDaySo.getText().toString();
            if (currentSequence.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập dãy số trước", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                String[] numbersStr = currentSequence.split("\\s+");
                int[] numbers = new int[numbersStr.length];
                for (int i = 0; i < numbersStr.length; i++) {
                    numbers[i] = Integer.parseInt(numbersStr[i]);
                }
                Arrays.sort(numbers);

                StringJoiner joiner = new StringJoiner(" ");
                for (int num : numbers) {
                    joiner.add(String.valueOf(num));
                }
                tvDaySo.setText(joiner.toString());
                Toast.makeText(this, "Đã sắp xếp dãy số", Toast.LENGTH_SHORT).show();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Dãy số không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });


        btnTimKiem1So.setOnClickListener(v -> {
            String currentSequence = tvDaySo.getText().toString();
            if (currentSequence.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập dãy số trước", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("dayso", currentSequence);
            startActivity(intent);
        });



        btnDong.setOnClickListener(v -> finish());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}