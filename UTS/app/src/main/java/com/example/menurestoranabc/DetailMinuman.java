package com.example.menurestoranabc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMinuman extends AppCompatActivity {
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_minuman);

        //coba navigate
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //end

        ImageView imgMinuman = findViewById(R.id.img_menu);
        TextView namaMinuman = findViewById(R.id.nama_menu);
        TextView hargaMinuman = findViewById(R.id.harga_menu);
        TextView detailMinuman = findViewById(R.id.detail_menu);

        Minuman minuman = getIntent().getParcelableExtra(ITEM_EXTRA);
        if (minuman != null){
            Glide.with(this)
                    .load(minuman.getFoto())
                    .into(imgMinuman);
            namaMinuman.setText(minuman.getNama());
            hargaMinuman.setText(minuman.getHarga());
            detailMinuman.setText(minuman.getDetail());

        }

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Minuman");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
