package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
//import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AntarBank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.antar_bank);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.antarBankPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int currentNumber = getIntent().getIntExtra("currentBalance", 0);
        String norek = getIntent().getStringExtra("norek");

//        Spinner pilihBank = findViewById(R.id.namaBank);

        EditText norekTujuan = findViewById(R.id.norekTujuan);

        EditText jumlahUang = findViewById(R.id.jumlahUang);

//        Spinner pilihLayanan = findViewById(R.id.pilihLayanan);

        EditText berita = findViewById(R.id.isiBerita);

        TextView dariRekening = findViewById(R.id.dariRekening);
        dariRekening.setText(norek);

        ImageButton send = findViewById(R.id.sendAntarBank);

        LayoutInflater inflater = getLayoutInflater();

        send.setOnClickListener(v -> {
            String norekTujuanText = norekTujuan.getText().toString().trim();
            String jumlahUangText = jumlahUang.getText().toString().trim();
            String beritaText = berita.getText().toString().trim();

            if (norekTujuanText.isEmpty() || jumlahUangText.isEmpty() || beritaText.isEmpty()){
                View view = inflater.inflate(R.layout.antarbank_salah,
                        findViewById(R.id.toast_layout_root_transfer_salah));
                Toast toastKosong = new Toast(this);
                toastKosong.setDuration(Toast.LENGTH_SHORT);
                toastKosong.setView(view);
                toastKosong.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
                toastKosong.show();
            } else{
                int uang = Integer.parseInt(jumlahUangText);
                Intent intent = new Intent(AntarBank.this, TransferSuccessFailed.class);
                intent.putExtra("currentBalance", currentNumber);
                intent.putExtra("norek", norek);
                intent.putExtra("norekTujuan", norekTujuanText);
                intent.putExtra("saldoKirim", uang);
                intent.putExtra("berita", beritaText);
                startActivity(intent);
                finish();

            }
        });




    }
}