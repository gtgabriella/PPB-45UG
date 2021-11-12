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

public class ListMinumanAdapter extends RecyclerView.Adapter<ListMinumanAdapter.ListViewHolder> {

    //variabel
    private ArrayList<Minuman> listMinuman;
    private ListMinumanAdapter.OnItemClickCallback onItemClickCallback;

    public ListMinumanAdapter (ArrayList<Minuman> listMinuman){
        this.listMinuman = listMinuman;
    }

    public void setOnItemClickCallback(ListMinumanAdapter.OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_makanan, parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMinumanAdapter.ListViewHolder holder, int position) {
        Minuman minuman = listMinuman.get(position);
        Glide.with(holder.itemView.getContext())
                .load(minuman.getFoto())
                .apply(new RequestOptions().override(70,70)) //sesuai width dan height pada xml
                .into(holder.imgPhotoMinuman);
        holder.namaMinuman.setText(minuman.getNama());
        holder.hargaMinuman.setText(minuman.getHarga());

        //callback
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listMinuman.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMinuman.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhotoMinuman;
        TextView namaMinuman, hargaMinuman;

        ListViewHolder(View itemview) {
            super(itemview);
            imgPhotoMinuman = itemview.findViewById(R.id.img_menu);
            namaMinuman = itemview.findViewById(R.id.nama_menu);
            hargaMinuman = itemview.findViewById(R.id.harga_menu);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Minuman data);
    }
}