package com.example.belajaruts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //property
    private ArrayList<String> foto2Menu = new ArrayList<>();
    private ArrayList<String> nama2Menu = new ArrayList<>();
    private ArrayList<String> harga2Menu = new ArrayList<>();
    private Context context;

    //konstruktor recycler. Generate, select all, OK.


    public RecyclerViewAdapter(ArrayList<String> foto2Menu, ArrayList<String> nama2Menu, ArrayList<String> harga2Menu, FragmentActivity context) {
        this.foto2Menu = foto2Menu;
        this.nama2Menu = nama2Menu;
        this.harga2Menu = harga2Menu;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_layout_adapter, parent, false); //Inflate layout, memasukkan view yang ada di design ke recylernya.
        ViewHolder holder = new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        //proses memasukkan data masing2 item
        //Gradle Scripts -> build.graddle (Module) -> add at the lowest position
        //  implementation 'com.github.bumptech.glide:glide:4.10.0'
        //  annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
        // Sync now

        Glide.with(context).asBitmap().load(foto2Menu.get(position)).into(holder.fotoMenu);
        holder.namaMenu.setText(nama2Menu.get(position));
        holder.hargaMenu.setText(harga2Menu.get(position));

    }

    @Override
    public int getItemCount() {
        return nama2Menu.size(); //untuk menentukan item urutan ke berapa (jumlah item, sesuaikan jika menggunakan DB)
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoMenu;
        TextView namaMenu;
        TextView hargaMenu;
        ConstraintLayout constraintLayout;

        //konstruktor class ViewHolder. Generate, select none.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoMenu = itemView.findViewById(R.id.fotoMenu);
            namaMenu = itemView.findViewById(R.id.namaMenu);
            hargaMenu = itemView.findViewById(R.id.hargaMenu);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
