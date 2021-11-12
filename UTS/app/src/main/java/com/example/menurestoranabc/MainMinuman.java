package com.example.menurestoranabc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class MainMinuman extends AppCompatActivity {

    //var
    private RecyclerView recyclerViewMinuman;
    private ArrayList<Minuman> listMinuman = new ArrayList<>();

    //spinner
    Spinner combo;
    public String menu[] = {"MINUMAN", "MAKANAN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_minuman);

        //start
        recyclerViewMinuman = findViewById(R.id.recyclerMenu);
        recyclerViewMinuman.setHasFixedSize(true);

        listMinuman.addAll(DataMinuman.getListData());
        showRecyclerList();

        //spinner
        combo = (Spinner) findViewById(R.id.comboData);
        ArrayAdapter adapter = new ArrayAdapter(MainMinuman.this, R.layout.support_simple_spinner_dropdown_item, menu) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextSize(25);
                ((TextView) v).setGravity(Gravity.CENTER);
                return v;
            }
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setGravity(Gravity.CENTER_HORIZONTAL);
                return v;
            }
        };
        combo.setAdapter(adapter);
        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("MINUMAN")) {

                } else {
                    if (parent.getItemAtPosition(position).equals("MAKANAN")) {
                        Intent intent = new Intent(MainMinuman.this, MainActivity.class);
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
        recyclerViewMinuman.setLayoutManager(new LinearLayoutManager(this));
        ListMinumanAdapter listMinumanAdapter = new ListMinumanAdapter(listMinuman);
        recyclerViewMinuman.setAdapter(listMinumanAdapter);

        //callback
        listMinumanAdapter.setOnItemClickCallback(new ListMinumanAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Minuman minuman) {
                Intent moveIntent = new Intent(MainMinuman.this, DetailMinuman.class);
                moveIntent.putExtra(DetailMinuman.ITEM_EXTRA, minuman);
                startActivity(moveIntent);
            }
        });
    }
}
