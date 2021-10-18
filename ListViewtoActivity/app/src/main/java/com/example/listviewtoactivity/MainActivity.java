package com.example.listviewtoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    //    Jika data dari array
    ListView listView;
    Spinner combo;

    public String name[]={"Hinata", "Kageyama", "Daichi", "Sugawara", "Asahi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listdata);
        combo = (Spinner) findViewById(R.id.combodata);

//        Buat adapter
//        ArrayAdapter namaAdapter = new ArrayAdapter(Class yang menjadi tempatnya)
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, name);
        listView.setAdapter(adapter);
        combo.setAdapter(adapter);
    }
}