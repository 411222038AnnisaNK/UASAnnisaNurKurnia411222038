package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText accessCodeInput = findViewById(R.id.accessCode);
        Button loginButton = findViewById(R.id.buttonLogin);
        Button biometricButton = findViewById(R.id.buttonFinger);

        biometricButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Biometric.class);
            startActivity(intent);
        });

        LayoutInflater inflater = getLayoutInflater();
        loginButton.setOnClickListener(v -> {
            String accessCode = accessCodeInput.getText().toString();

            if (accessCode.isEmpty()) {
                View view = inflater.inflate(R.layout.toast_login,
                        findViewById(R.id.toast_layout_root_access));
                Toast toastKosong = new Toast(this);
                toastKosong.setDuration(Toast.LENGTH_SHORT);
                toastKosong.setView(view);
                toastKosong.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
                toastKosong.show();
            } else if (!accessCode.equals("1717171717")) {
                View view = inflater.inflate(R.layout.toast_login_salah,
                        findViewById(R.id.toast_layout_root_access_salah));
                Toast toastSalah = new Toast(this);
                toastSalah.setDuration(Toast.LENGTH_SHORT);
                toastSalah.setView(view);
                toastSalah.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
                toastSalah.show();
            }else {
                Intent intent = new Intent(Login.this, Dashboard.class);
                startActivity(intent);

                finish();
            }
        });
    }
}