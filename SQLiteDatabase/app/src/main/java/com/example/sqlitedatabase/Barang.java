package com.example.sqlitedatabase;

public class Barang {
    private String idbarang;
    private String barang;
    private String stok;
    private String harga;

    public Barang(String idbarang, String barang, String stok, String harga) {
        this.idbarang = idbarang;
        this.barang = barang;
        this.stok = stok;
        this.harga = harga;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public String getBarang() {
        return barang;
    }

    public String getStok() {
        return stok;
    }

    public String getHarga() {
        return harga;
    }
}
