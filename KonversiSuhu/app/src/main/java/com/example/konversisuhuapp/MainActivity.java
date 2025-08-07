package com.example.konversisuhuapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etNilai;
    TextView tvHasil;
    Spinner spinnerKonversi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
    }

    // Inisialisasi komponen UI
    private void load() {
        etNilai = findViewById(R.id.etNilai);
        tvHasil = findViewById(R.id.tvHasil);
        spinnerKonversi = findViewById(R.id.spinnerKonversi);
    }

    // Dipanggil saat tombol konversi diklik
    public void onClickKonversi(View view) {
        String nilaiInput = etNilai.getText().toString().trim();
        if (nilaiInput.isEmpty()) {
            Toast.makeText(this, "Nilai tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        double nilai;
        try {
            nilai = Double.parseDouble(nilaiInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        String pilihan = spinnerKonversi.getSelectedItem().toString();
        double hasil = 0;

            if (pilihan.equals("Celsius ke Reamur")) {
        hasil = cToR(nilai);
    } else if (pilihan.equals("Celsius ke Fahrenheit")) {
        hasil = cToF(nilai);
    } else if (pilihan.equals("Celsius ke Kelvin")) {
        hasil = cToK(nilai);
    } else if (pilihan.equals("Reamur ke Celsius")) {
        hasil = rToC(nilai);
    } else if (pilihan.equals("Reamur ke Fahrenheit")) {
        hasil = rToF(nilai);
    } else if (pilihan.equals("Reamur ke Kelvin")) {
        hasil = rToK(nilai);
    } else if (pilihan.equals("Fahrenheit ke Celsius")) {
        hasil = fToC(nilai);
    } else if (pilihan.equals("Fahrenheit ke Reamur")) {
        hasil = fToR(nilai);
    } else if (pilihan.equals("Fahrenheit ke Kelvin")) {
        hasil = fToK(nilai);
    } else if (pilihan.equals("Kelvin ke Celsius")) {
        hasil = kToC(nilai);
    } else if (pilihan.equals("Kelvin ke Reamur")) {
        hasil = kToR(nilai);
    } else if (pilihan.equals("Kelvin ke Fahrenheit")) {
        hasil = kToF(nilai);
        } else {
            Toast.makeText(this, "Pilihan konversi tidak dikenali", Toast.LENGTH_SHORT).show();
            return;
        }

            tvHasil.setText(String.format("%.2f", hasil));
    }

    // Fungsi konversi
    private double cToR(double c) {
        return 0.8 * c; // R = 4/5 * C
    }

    private double cToF(double c) {
        return (1.8 * c) + 32; // F = (9/5 * C) + 32
    }

    private double cToK(double c) {
        return c + 273.15; // K = C + 273.15
    }

    private double rToC(double r) {
        return r / 0.8; // C = R / (4/5)
    }

    private double rToF(double r) {
        return (r / 0.8) * 1.8 + 32; // F = (R / (4/5)) * (9/5) + 32
    }
    private double rToK(double r) {
    return (r / 0.8) + 273.15; // C = R / 0.8, lalu C ke K
    }

    private double fToC(double f) {
        return (f - 32) / 1.8;
    }

    private double fToR(double f) {
        return (f - 32) / 2.25; // (F - 32) * 4/9
    }

    private double fToK(double f) {
        return (f - 32) / 1.8 + 273.15;
    }

    private double kToC(double k) {
        return k - 273.15;
    }

    private double kToR(double k) {
        return (k - 273.15) * 0.8;
    }

    private double kToF(double k) {
        return (k - 273.15) * 1.8 + 32;
    }

}