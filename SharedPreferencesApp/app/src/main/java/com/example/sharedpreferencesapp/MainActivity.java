package com.example.sharedpreferencesapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Deklarasi komponen UI
    private EditText etNamaBarang, etStokBarang;
    private Button btnSimpan, btnTampil;
    private TextView tvHasil;

    // Kunci untuk SharedPreferences
    private static final String PREF_NAME = "DataBarangPrefs";
    private static final String KEY_NAMA_BARANG = "nama_barang";
    private static final String KEY_STOK_BARANG = "stok_barang";

    // SharedPreferences instance
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Mengatur padding untuk system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi komponen UI
        initViews();

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Setup listener untuk tombol
        setupButtonListeners();
    }

    /**
     * Method untuk menginisialisasi semua komponen UI
     */
    private void initViews() {
        etNamaBarang = findViewById(R.id.etNamaBarang);
        etStokBarang = findViewById(R.id.etStokBarang);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnTampil = findViewById(R.id.btnTampil);
        tvHasil = findViewById(R.id.tvHasil);
    }

    /**
     * Method untuk setup listener pada tombol Simpan dan Tampil
     */
    private void setupButtonListeners() {
        // Listener untuk tombol Simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        // Listener untuk tombol Tampil
        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilkanData();
            }
        });
    }

    /**
     * Method untuk menyimpan data ke SharedPreferences
     * Mengambil input dari EditText dan menyimpannya secara permanen
     */
    private void simpanData() {
        try {
            // Mengambil nilai dari input
            String namaBarang = etNamaBarang.getText().toString().trim();
            String stokBarangStr = etStokBarang.getText().toString().trim();

            // Validasi input
            if (namaBarang.isEmpty() || stokBarangStr.isEmpty()) {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
                return;
            }

            int stokBarang = Integer.parseInt(stokBarangStr);

            // Menyimpan data ke SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAMA_BARANG, namaBarang);
            editor.putInt(KEY_STOK_BARANG, stokBarang);
            editor.apply(); // apply() untuk penyimpanan asynchronous

            // Menampilkan pesan sukses
            Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();

            // Mengosongkan input fields
            etNamaBarang.setText("");
            etStokBarang.setText("");

        } catch (NumberFormatException e) {
            // Menangani error jika input stok bukan angka
            Toast.makeText(this, "Stok barang harus berupa angka", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Menangani error umum
            Toast.makeText(this, "Terjadi kesalahan saat menyimpan data", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method untuk menampilkan data dari SharedPreferences
     * Membaca data yang tersimpan dan menampilkannya di TextView
     */
    private void tampilkanData() {
        try {
            // Mengambil data dari SharedPreferences
            String namaBarang = sharedPreferences.getString(KEY_NAMA_BARANG, "");
            int stokBarang = sharedPreferences.getInt(KEY_STOK_BARANG, 0);

            // Memeriksa apakah ada data yang tersimpan
            if (namaBarang.isEmpty()) {
                tvHasil.setText("Data belum tersimpan");
                Toast.makeText(this, "Belum ada data yang disimpan", Toast.LENGTH_SHORT).show();
                return;
            }

            // Menampilkan data di TextView
            String hasilText = "Nama Barang: " + namaBarang + "\nStok Barang: " + stokBarang;
            tvHasil.setText(hasilText);

            // Menampilkan pesan sukses
            Toast.makeText(this, "Data berhasil ditampilkan", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // Menangani error
            tvHasil.setText("Error saat membaca data");
            Toast.makeText(this, "Terjadi kesalahan saat membaca data", Toast.LENGTH_SHORT).show();
        }
    }
}
