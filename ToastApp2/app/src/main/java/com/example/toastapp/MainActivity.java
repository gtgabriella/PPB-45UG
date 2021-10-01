package com.example.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView angkaKounter;
//    menampung angka dalam kounter
    int angka = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angkaKounter = findViewById(R.id.kounter);
    }

    public void toastklik(View view) {
        if (angka == 0) {
            angka = 0;
            Toast.makeText(this, "CANT BE TOASTED, IT'S ZERO!", Toast.LENGTH_SHORT).show();
        } else {
            angka = angka - 1;
            angkaKounter.setText(Integer.toString(angka));
            Toast.makeText(this, "TOASTED", Toast.LENGTH_SHORT).show();
        }
    }

    public void countklik(View view) {
        Toast.makeText(this, "COUNTED", Toast.LENGTH_SHORT).show();
        angka = angka+1;
        angkaKounter.setText(Integer.toString(angka));
    }
}