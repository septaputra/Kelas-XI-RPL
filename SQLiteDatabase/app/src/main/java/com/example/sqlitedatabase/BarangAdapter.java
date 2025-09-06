package com.example.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {

    private final Context context;
    private final ArrayList<Barang> listBarang;
    private final Database database;
    private final OnDataChangedListener listener;

    // Interface untuk komunikasi dengan MainActivity
    public interface OnDataChangedListener {
        void onDataChanged();
    }

    public BarangAdapter(Context context, ArrayList<Barang> listBarang, Database database, OnDataChangedListener listener) {
        this.context = context;
        this.listBarang = listBarang;
        this.database = database;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        Barang barang = listBarang.get(position);

        // Set data ke view
        holder.tvNamaBarang.setText(barang.getBarang());
        holder.tvStok.setText("Stok: " + barang.getStok());
        holder.tvHarga.setText("Harga: Rp " + barang.getHarga());

        // Listener menu popup
        holder.tvMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_item, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.ubah) {
                    Toast.makeText(context, "Ubah item " + barang.getBarang(), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.hapus) {
                    // Implementasi hapus data
                    String id = barang.getIdbarang();
                    boolean isDeleted = database.deleteData(id);

                    if (isDeleted) {
                        // Hapus dari list dan refresh RecyclerView
                        int pos = holder.getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listBarang.remove(pos);
                            notifyItemRemoved(pos);
                            notifyItemRangeChanged(pos, listBarang.size());
                        }

                        Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

                        // Panggil listener ke MainActivity
                        if (listener != null) {
                            listener.onDataChanged();
                        }
                    } else {
                        Toast.makeText(context, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBarang, tvStok, tvHarga, tvMenu;

        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang);
            tvStok = itemView.findViewById(R.id.tv_stok);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvMenu = itemView.findViewById(R.id.tv_menu);
        }
    }
}
