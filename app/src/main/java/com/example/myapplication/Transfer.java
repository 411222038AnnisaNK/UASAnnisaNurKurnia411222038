package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ImageButton;

public class Transfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.transfer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.transferPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton antarBank = findViewById(R.id.antarbankButton);
        int currentNumber = getIntent().getIntExtra("currentBalance", 0);
        String norek = getIntent().getStringExtra("norek");

        antarBank.setOnClickListener(v -> {
            Intent antarbank = new Intent(Transfer.this, AntarBank.class);
            antarbank.putExtra("currentBalance", currentNumber);
            antarbank.putExtra("norek", norek);
            startActivity(antarbank);
            finish();
        });


    }
}