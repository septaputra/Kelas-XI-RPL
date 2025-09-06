# TODO - Aplikasi SharedPreferences

## Langkah-langkah Implementasi:

1. [x] Update layout activity_main.xml untuk menambahkan UI komponen
2. [x] Update strings.xml untuk menambahkan string resources
3. [x] Update MainActivity.java untuk implementasi SharedPreferences
4. [ ] Testing aplikasi

## Komponen yang telah ditambahkan:

### Layout (activity_main.xml):
- EditText untuk Nama Barang dengan hint "Masukkan nama barang"
- EditText untuk Stok Barang dengan inputType number dan hint "Masukkan stok barang"
- Button Simpan dengan teks "Simpan" (dalam LinearLayout untuk posisi tengah)
- Button Tampil dengan teks "Tampil" (dalam LinearLayout untuk posisi tengah)
- TextView untuk menampilkan hasil data dengan posisi di bawah tombol

### String Resources (strings.xml):
- Hint untuk input fields
- Teks untuk tombol
- Label dan pesan error

### Kode (MainActivity.java):
- Inisialisasi SharedPreferences dengan kunci KEY_NAMA_BARANG dan KEY_STOK_BARANG
- Logika penyimpanan data dengan validasi input
- Logika pembacaan data dari SharedPreferences
- Handler untuk tombol klik dengan exception handling
- Komentar yang jelas untuk setiap method
