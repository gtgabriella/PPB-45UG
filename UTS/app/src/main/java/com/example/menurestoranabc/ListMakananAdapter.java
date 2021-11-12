package com.example.menurestoranabc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListMakananAdapter extends RecyclerView.Adapter<ListMakananAdapter.ListViewHolder> {
    //variabel
    private ArrayList<Makanan> listMakanan;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;

    }

    public ListMakananAdapter (ArrayList<Makanan> listMakanan){
        this.listMakanan = listMakanan;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_makanan, parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMakananAdapter.ListViewHolder holder, int position) {
        Makanan makanan = listMakanan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(makanan.getFoto())
                .apply(new RequestOptions().override(70,70)) //sesuai width dan height pada xml
                .into(holder.imgPhoto);
        holder.namaMakanan.setText(makanan.getNama());
        holder.hargaMakanan.setText(makanan.getHarga());

        //callback
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listMakanan.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMakanan.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView namaMakanan, hargaMakanan;

        ListViewHolder(View itemview) {
            super(itemview);
            imgPhoto = itemview.findViewById(R.id.img_menu);
            namaMakanan = itemview.findViewById(R.id.nama_menu);
            hargaMakanan = itemview.findViewById(R.id.harga_menu);
        }
    }

    //callback
    public interface OnItemClickCallback{
        void onItemClicked(Makanan data);
    }

}
