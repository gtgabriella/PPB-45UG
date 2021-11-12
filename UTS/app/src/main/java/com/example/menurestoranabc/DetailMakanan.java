package com.example.menurestoranabc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMakanan extends AppCompatActivity {
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_makanan);

        //coba navigate
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //end

        ImageView imgMakanan = findViewById(R.id.img_menu);
        TextView namaMakanan = findViewById(R.id.nama_menu);
        TextView hargaMakanan = findViewById(R.id.harga_menu);
        TextView detailMakanan = findViewById(R.id.detail_menu);

        Makanan makanan = getIntent().getParcelableExtra(ITEM_EXTRA);
        if (makanan != null){
            Glide.with(this)
                    .load(makanan.getFoto())
                    .into(imgMakanan);
            namaMakanan.setText(makanan.getNama());
            hargaMakanan.setText(makanan.getHarga());
            detailMakanan.setText(makanan.getDetail());

        }

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Makanan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
