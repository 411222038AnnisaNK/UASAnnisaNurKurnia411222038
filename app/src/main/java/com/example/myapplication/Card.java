package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ImageButton;
import android.widget.TextView;

public class Card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cardPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String currentNumber = getIntent().getStringExtra("currentBalance");
        String norek = getIntent().getStringExtra("norek");

        TextView nomorKartu = findViewById(R.id.textViewNomorKartu);
        nomorKartu.setText("1212 1212 1212 1212");
        TextView noRek = findViewById(R.id.textViewNoRek);
        noRek.setText(norek);
        TextView saldo = findViewById(R.id.textViewSaldo);
        saldo.setText(currentNumber);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());


    }
}