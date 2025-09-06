package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    
    // Konstanta untuk database
    private static final String DATABASE_NAME = "dbtoko";
    private static final int VERSION = 1;
    
    // Variabel untuk akses database
    private SQLiteDatabase db;
    
    // Constructor
    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel tblbarang sesuai spesifikasi
        String createTableBarang = "CREATE TABLE tblbarang (" +
                "idbarang INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "barang TEXT, " +
                "stok REAL, " +
                "harga REAL)";
        
        // Eksekusi query pembuatan tabel
        db.execSQL(createTableBarang);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Menghapus tabel lama jika versi database ditingkatkan
        db.execSQL("DROP TABLE IF EXISTS tblbarang");
        
        // Membuat tabel baru
        onCreate(db);
    }
    
    // Metode untuk mendapatkan akses ke database yang bisa ditulis
    public SQLiteDatabase db() {
        return this.getWritableDatabase();
    }
    
    // Metode untuk mengeksekusi query SQL dengan penanganan exception
    public boolean runSQL(String sql) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Metode untuk membuat tabel tblbarang
    public void buatTabel() {
        String sql = "CREATE TABLE IF NOT EXISTS tblbarang (" +
                "idbarang INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "barang TEXT, " +
                "stok REAL, " +
                "harga REAL)";
        runSQL(sql);
    }
    
    // Metode untuk menyimpan barang menggunakan ContentValues
    public boolean simpanBarang(String namaBarang, double stok, double harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put("barang", namaBarang);
        values.put("stok", stok);
        values.put("harga", harga);
        
        long result = db.insert("tblbarang", null, values);
        return result != -1;
    }
    
    // Metode untuk membaca data dari tabel tblbarang
    public ArrayList<Barang> selectData() {
        ArrayList<Barang> listBarang = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        
        String sql = "SELECT * FROM tblbarang ORDER BY barang ASC";
        Cursor cursor = db.rawQuery(sql, null);
        
        if (cursor.moveToFirst()) {
            do {
                String idbarang = cursor.getString(cursor.getColumnIndexOrThrow("idbarang"));
                String barang = cursor.getString(cursor.getColumnIndexOrThrow("barang"));
                String stok = cursor.getString(cursor.getColumnIndexOrThrow("stok"));
                String harga = cursor.getString(cursor.getColumnIndexOrThrow("harga"));
                
                listBarang.add(new Barang(idbarang, barang, stok, harga));
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        return listBarang;
    }

    // Metode untuk menghapus data berdasarkan id
    public boolean deleteData(String id) {
        String sql = "DELETE FROM tblbarang WHERE idbarang = '" + id + "'";
        return runSQL(sql);
    }

    // Metode untuk mengambil satu baris data berdasarkan ID
    public Barang selectUpdate(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM tblbarang WHERE idbarang = '" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        
        Barang barang = null;
        if (cursor.moveToFirst()) {
            String idbarang = cursor.getString(cursor.getColumnIndexOrThrow("idbarang"));
            String barangName = cursor.getString(cursor.getColumnIndexOrThrow("barang"));
            String stok = cursor.getString(cursor.getColumnIndexOrThrow("stok"));
            String harga = cursor.getString(cursor.getColumnIndexOrThrow("harga"));
            
            barang = new Barang(idbarang, barangName, stok, harga);
        }
        
        cursor.close();
        return barang;
    }

    // Metode untuk update data
    public boolean updateData(String id, String namaBarang, double stok, double harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put("barang", namaBarang);
        values.put("stok", stok);
        values.put("harga", harga);
        
        int result = db.update("tblbarang", values, "idbarang = ?", new String[]{id});
        return result > 0;
    }
}
