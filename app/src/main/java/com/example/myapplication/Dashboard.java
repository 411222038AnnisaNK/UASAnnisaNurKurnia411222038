package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

import android.widget.ImageButton;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    private int currentNumber = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboardPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        if (getIntent().hasExtra("updatedBalance")){
            currentNumber = getIntent().getIntExtra("updatedBalance", currentNumber);
        }

        TextView textView = findViewById(R.id.balance);
        textView.setText(formatNumber(currentNumber));

        ImageButton cardButton = findViewById(R.id.cardButton);
        cardButton.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, Card.class);
            intent.putExtra("currentBalance", formatNumber(currentNumber));
            intent.putExtra("norek", "1212-1212-1212");
            startActivity(intent);
        });

        ImageButton transferButton = findViewById(R.id.transferButton);

        transferButton.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this, Transfer.class);
            intent.putExtra("currentBalance", currentNumber);
            intent.putExtra("norek", "1212-1212-1212");
            startActivity(intent);
        });

    }

    private String formatNumber(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        return numberFormat.format(number);
    }
}