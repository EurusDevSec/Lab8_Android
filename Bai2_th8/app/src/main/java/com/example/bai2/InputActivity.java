package com.example.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    private EditText edtDaySo;
    private Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        setTitle("Nhập dãy số");
        edtDaySo = findViewById(R.id.edtDaySo);
        btnXacNhan = findViewById(R.id.btnXacNhan);

        btnXacNhan.setOnClickListener(v -> {
            String daySo = edtDaySo.getText().toString().trim();
            if (!daySo.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("dayso", daySo);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}