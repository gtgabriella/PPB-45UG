package com.example.menurestoranabc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //var
    private RecyclerView recyclerViewMakanan;
    private ArrayList<Makanan> list = new ArrayList<>();

    //spinner
    Spinner combo;
    public String menu[]={"MAKANAN", "MINUMAN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start
        recyclerViewMakanan = findViewById(R.id.recyclerMenu);
        recyclerViewMakanan.setHasFixedSize(true);

        list.addAll(DataMakanan.getListData());
        showRecyclerList();

        //spinner
        combo = (Spinner) findViewById(R.id.comboData);
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, menu){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(25);
                ((TextView) v).setGravity(Gravity.CENTER);
                return v;
            };
            public View getDropDownView(int position, View convertView,ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,parent);
                ((TextView) v).setGravity(Gravity.CENTER_HORIZONTAL);
                return v;
            }
        };
        combo.setAdapter(adapter);

        //biar kalo dipejet pindah halaman
        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("MAKANAN")){

                } else {
                    if (parent.getItemAtPosition(position).equals("MINUMAN")){
                        Intent intent = new Intent(MainActivity.this, MainMinuman.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void showRecyclerList(){
        recyclerViewMakanan.setLayoutManager(new LinearLayoutManager(this));
        ListMakananAdapter listMakananAdapter = new ListMakananAdapter(list);
        recyclerViewMakanan.setAdapter(listMakananAdapter);

        //callback
        listMakananAdapter.setOnItemClickCallback(new ListMakananAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Makanan makanan) {
                Intent moveIntent = new Intent(MainActivity.this, DetailMakanan.class);
                moveIntent.putExtra(DetailMakanan.ITEM_EXTRA, makanan);
                startActivity(moveIntent);
            }
        });
    }
}