package com.example.bai3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);
        Button btnLogin = findViewById(R.id.btnLogin);

        DBHelper dbHelper = new DBHelper(this);
        String[] creds = dbHelper.getCredentials();
        if (creds != null) {
            etUsername.setText(creds[0]);
            etPassword.setText(creds[1]);
            cbRemember.setChecked(true);
        }

        btnLogin.setOnClickListener(v -> {
            String user = etUsername.getText().toString().trim();
            String pass = etPassword.getText().toString();
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(MainActivity.this, getString(R.string.msg_empty), Toast.LENGTH_SHORT).show();
                return;
            }

            if (cbRemember.isChecked()) {
                dbHelper.saveCredentials(user, pass);
            } else {
                dbHelper.clearCredentials();
            }

            Toast.makeText(MainActivity.this, getString(R.string.msg_success), Toast.LENGTH_SHORT).show();
        });
    }
}