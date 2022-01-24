package com.example.vid1112;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.example.firebase.R;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDBCreate extends AppCompatActivity{
    private DatabaseReference database;
    private Button btSubmit;
    private EditText etNama;
    private EditText etNim;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbcreate);

        etNama = (EditText) findViewById(R.id.et_nmmhs);
        etNim = (EditText) findViewById(R.id.et_nimmhs);

        btSubmit = (Button) findViewById(R.id.bt_submit);
        database = FirebaseDatabase.getInstance().getReference();
        final Mahasiswa mahasiswa = (Mahasiswa)
                getIntent().getSerializableExtra("data");

        if (mahasiswa != null) {
            etNama.setText(mahasiswa.getNmmhs());
            etNim.setText(mahasiswa.getNimmhs());

            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mahasiswa.setNmmhs(etNama.getText().toString());
                    mahasiswa.setNimmhs(etNim.getText().toString());
                    updateMahasiswa(mahasiswa);
                }
            });
        } else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmpty(etNama.getText().toString()) && !isEmpty(etNim.getText().toString()))
                        submitMahasiswa(new Mahasiswa());
                    else Snackbar.make(findViewById(R.id.bt_submit),"Data mahasiswa tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etNama.getWindowToken(),0);
                }
            });
        }
    }
    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    private void updateMahasiswa(Mahasiswa mahasiswa) {
        database.child("mahasiswa")
                .child(mahasiswa.getKey())
                .setValue(mahasiswa)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitMahasiswa(Mahasiswa mahasiswa) {
        database.child("mahasiswa").push().setValue(mahasiswa).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNama.setText("");
                etNim.setText("");

                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, FirebaseDBCreate.class);
    }
}
