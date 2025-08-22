package com.example.sqlitedatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Database database;
    private EditText etNamaBarang, etStok, etHarga;
    private Button btnSimpan;
    private RecyclerView rvBarang;
    private BarangAdapter barangAdapter;
    private ArrayList<Barang> listBarang;
    private String idBarangUpdate = ""; // Menyimpan ID untuk update

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi database dan UI
        initDatabase();
        initUI();
        loadData();
    }

    // Inisialisasi database
    private void initDatabase() {
        database = new Database(this);
        database.buatTabel(); // Membuat tabel tblbarang jika belum ada
    }

    // Inisialisasi UI
    private void initUI() {
        etNamaBarang = findViewById(R.id.et_nama_barang);
        etStok = findViewById(R.id.et_stok);
        etHarga = findViewById(R.id.et_harga);
        btnSimpan = findViewById(R.id.btn_simpan);

        rvBarang = findViewById(R.id.rv_barang);
        rvBarang.setLayoutManager(new LinearLayoutManager(this));

        // Event tombol simpan
        btnSimpan.setOnClickListener(this::simpanData);
    }

    // Load data dari database ke RecyclerView
    private void loadData() {
        listBarang = database.selectData();
        barangAdapter = new BarangAdapter(this, listBarang, database, this::loadData);
        rvBarang.setAdapter(barangAdapter);
    }

    // Pilih data untuk diupdate
    public void selectUpdate(String id) {
        Barang barang = database.selectUpdate(id);
        if (barang != null) {
            etNamaBarang.setText(barang.getBarang());
            etStok.setText(String.valueOf(barang.getStok()));
            etHarga.setText(String.valueOf(barang.getHarga()));
            idBarangUpdate = id;
            btnSimpan.setText("UPDATE");
        }
    }

    // Simpan atau update data
    public void simpanData(View view) {
        String namaBarang = etNamaBarang.getText().toString().trim();
        String stokStr = etStok.getText().toString().trim();
        String hargaStr = etHarga.getText().toString().trim();

        if (namaBarang.isEmpty() || stokStr.isEmpty() || hargaStr.isEmpty()) {
            Toast.makeText(this, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double stok = Double.parseDouble(stokStr);
            double harga = Double.parseDouble(hargaStr);

            boolean isSuccess;

            if (btnSimpan.getText().toString().equals("UPDATE")) {
                // Update data
                isSuccess = database.updateData(idBarangUpdate, namaBarang, stok, harga);
                if (isSuccess) {
                    Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    btnSimpan.setText("SIMPAN");
                    idBarangUpdate = "";
                } else {
                    Toast.makeText(this, "Gagal mengubah data", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Simpan data baru
                isSuccess = database.simpanBarang(namaBarang, stok, harga);
                if (isSuccess) {
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                }
            }

            if (isSuccess) {
                // Clear input
                etNamaBarang.setText("");
                etStok.setText("");
                etHarga.setText("");

                // Refresh RecyclerView
                loadData();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Format stok atau harga tidak valid", Toast.LENGTH_SHORT).show();
        }
    }
}
