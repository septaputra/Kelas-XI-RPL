package com.example.recycleviewcardview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SiswaAdapter adapter;
    private List<Siswa> siswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // Data awal siswa (12 siswa)
        siswaList = new ArrayList<>();
        siswaList.add(new Siswa("Andi", "Bandung"));
        siswaList.add(new Siswa("Budi", "Jakarta"));
        siswaList.add(new Siswa("Citra", "Surabaya"));
        siswaList.add(new Siswa("Dewi", "Yogyakarta"));
        siswaList.add(new Siswa("Eka", "Medan"));
        siswaList.add(new Siswa("Fajar", "Bogor"));
        siswaList.add(new Siswa("Gina", "Depok"));
        siswaList.add(new Siswa("Hendra", "Malang"));
        siswaList.add(new Siswa("Indah", "Palembang"));
        siswaList.add(new Siswa("Joko", "Makassar"));
        siswaList.add(new Siswa("Kiki", "Solo"));
        siswaList.add(new Siswa("Lina", "Semarang"));

        // Hubungkan adapter
        adapter = new SiswaAdapter(this, siswaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Tambahkan jarak antar item
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spacingInPixels));
    }

    public void btnTambah(View v) {
        Toast.makeText(this, "Tombol Tambah Ditekan", Toast.LENGTH_SHORT).show();
        Siswa newSiswa = new Siswa("JONI RAMBO", "JAKARTA");
        siswaList.add(newSiswa);
        adapter.notifyDataSetChanged();
    }

    // Kelas ItemDecoration untuk menambahkan jarak vertikal antar item
    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            // Tambahkan jarak di bawah setiap item kecuali item terakhir
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = verticalSpaceHeight;
            }
        }
    }
}
