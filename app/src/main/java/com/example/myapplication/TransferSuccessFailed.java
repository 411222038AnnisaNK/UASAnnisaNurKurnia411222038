package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ImageButton;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class TransferSuccessFailed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.transfer_sukses_antarbank);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.postTransferAntarBank), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int currentBalance = getIntent().getIntExtra("currentBalance", 0);
        String norek = getIntent().getStringExtra("norek");
        String norekTujuan = getIntent().getStringExtra("norekTujuan");
        int saldoKirim = getIntent().getIntExtra("saldoKirim", 0);
        String berita = getIntent().getStringExtra("berita");

        String successfail = "BERHASIL";

        if (currentBalance < saldoKirim){
            successfail = "GAGAL";
        }else{
            currentBalance -= saldoKirim;
        }

        TextView dariRekening = findViewById(R.id.dariNorek);
        dariRekening.setText(String.format("Dari: %s", norek));

        TextView keRekening = findViewById(R.id.keNorek);
        keRekening.setText(String.format("Ke: %s", norekTujuan));

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        String formattedSaldo = currencyFormat.format(saldoKirim);
        formattedSaldo = formattedSaldo.replace("IDR", "Rp.").trim();
        TextView saldo = findViewById(R.id.saldoKirim);
        saldo.setText(formattedSaldo);

        TextView beritaTransfer = findViewById(R.id.berita);
        beritaTransfer.setText(berita);

        TextView berhasilGagal = findViewById(R.id.berhasilGagal);
        berhasilGagal.setText(successfail);

        ImageButton back = findViewById(R.id.backButton);
        int finalCurrentBalance = currentBalance;
        back.setOnClickListener(v -> {
            Intent intent = new Intent(TransferSuccessFailed.this, Dashboard.class);
            intent.putExtra("updatedBalance", finalCurrentBalance);
            startActivity(intent);
            finish();
        });

    }
}