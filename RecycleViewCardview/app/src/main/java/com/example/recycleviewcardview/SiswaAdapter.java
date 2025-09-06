package com.example.recycleviewcardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {

    private Context context;
    private List<Siswa> siswaList;

    public SiswaAdapter(Context context, List<Siswa> siswaList) {
        this.context = context;
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Siswa siswa = siswaList.get(position);
        holder.tvNama.setText(siswa.getNama());
        holder.tvAlamat.setText(siswa.getAlamat());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Nama: " + siswa.getNama() + "\nAlamat: " + siswa.getAlamat(), Toast.LENGTH_SHORT).show();
        });

        holder.tvMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.tvMenu);
            popupMenu.inflate(R.menu.menu_option);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int id = menuItem.getItemId();
                if (id == R.id.menu_simpan) {
                    Toast.makeText(context, "Data " + siswa.getNama() + " disimpan", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.menu_hapus) {
                    siswaList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, siswaList.size());
                    Toast.makeText(context, "Data " + siswa.getNama() + " dihapus", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat, tvMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvMenu = itemView.findViewById(R.id.tv_menu);
        }
    }
}
