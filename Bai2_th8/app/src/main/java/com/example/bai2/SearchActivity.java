package com.example.bai2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    private EditText edtSoCanTim;
    private Button btnTim;
    private Button btnQuayLai;
    private String daySo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Tìm kiếm số");
        edtSoCanTim = findViewById(R.id.edtSoCanTim);
        btnTim = findViewById(R.id.btnTim);
        btnQuayLai = findViewById(R.id.btnQuayLai);

        daySo = getIntent().getStringExtra("dayso");

        btnTim.setOnClickListener(v -> {
            String soCanTimStr = edtSoCanTim.getText().toString().trim();
            if (soCanTimStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số cần tìm", Toast.LENGTH_SHORT).show();
                return;
            }

            if (daySo == null || daySo.isEmpty()) {
                Toast.makeText(this, "Dãy số không tồn tại", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int soCanTim = Integer.parseInt(soCanTimStr);
                String[] numbersStr = daySo.split("\\s+");
                boolean found = false;
                for (String numStr : numbersStr) {
                    if (Integer.parseInt(numStr) == soCanTim) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    Toast.makeText(this, "Tìm thấy số " + soCanTim, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Không tìm thấy số " + soCanTim, Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Số nhập vào không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });

        btnQuayLai.setOnClickListener(v -> {
            finish();
        });
    }
}
