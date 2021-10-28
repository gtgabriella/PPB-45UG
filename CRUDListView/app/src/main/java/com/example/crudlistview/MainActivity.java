package com.example.crudlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //buat objek
    BantuDatabase bd = new BantuDatabase(this);
    //perkenalkan objek2 pada view
    public static ListView listView;
    public static EditText editTextName;
    EditText editTextDoB;
    EditText editTextPosition;
    EditText editTextMotto;
    Button tblTambah;

    ArrayAdapter adapter;
    ArrayList<String> listViewKu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //persiapkan
        listView = findViewById(R.id.listDataTokoh);
        editTextName = findViewById(R.id.name);
        editTextDoB = findViewById(R.id.dateOfBirth);
        editTextPosition = findViewById(R.id.position);
        editTextMotto = findViewById(R.id.motto);
        tblTambah = findViewById(R.id.tblTambah);

        //objek arraylist
        listViewKu = new ArrayList<>();
        tampilkan_tokoh();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final String noid = listViewKu.get(position);
                final String nomor = noid.substring(0,2);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Alert!")
                        .setMessage("Delete this data? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                bd.deleteTokoh(Integer.parseInt(nomor));
                                listViewKu.remove(position);
                                listView.invalidateViews();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return false;
            }
        });

        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.tambahData(editTextName.getText().toString(), editTextDoB.getText().toString(), editTextPosition.getText().toString(), editTextMotto.getText().toString());
                Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                listViewKu.clear();
                tampilkan_tokoh();
            }
        });
    }

    private void tampilkan_tokoh() {
        Cursor cursor = bd.tampilTokoh();
        if (cursor.getCount()==0){
            Toast.makeText(this, "Record Kosong", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                listViewKu.add(String.valueOf(cursor.getInt(0)) + ". " + cursor.getString(1) + " born at " + cursor.getString(2) + ", as " + cursor.getString(3) + " on the team. His motto is '" + cursor.getString(4) + "'.");
            }
            adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listViewKu);
            listView.setAdapter(adapter);
        }
    }

//    public void TambahData(View view) {
//        Intent intent = new Intent(MainActivity.this, AddData.class);
//        startActivity(intent);
//    }
}