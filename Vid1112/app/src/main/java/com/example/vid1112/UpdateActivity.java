package com.example.vid1112;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.example.firebase.R;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateActivity extends AppCompatActivity {
    private EditText nmMahasiswa, nimMahasiswa;

    private Button update, batal;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekNm, cekNim;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        nmMahasiswa = findViewById(R.id.editNama);
        nimMahasiswa = findViewById(R.id.editNIM);
        update = findViewById(R.id.act_update);
        batal = findViewById(R.id.btn_batal2);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekNm = nmMahasiswa.getText().toString();
                cekNim = nimMahasiswa.getText().toString();

                if(isEmpty(cekNm) || isEmpty(cekNim)) {
                    Toast.makeText(UpdateActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.setNmmhs(nmMahasiswa.getText().toString());
                    mhs.setNimmhs(nimMahasiswa.getText().toString());

                    updateMahasiswa(mhs);
                    Intent intent = new Intent(UpdateActivity.this, GetFirebaseActivity.class);
                    startActivity(intent);
                }
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, GetFirebaseActivity.class);
            }
        });
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void getData() {
        final String getNama = getIntent().getExtras().getString("nmmhs");
        final String getNim = getIntent().getExtras().getString("nimmhs");

        nmMahasiswa.setText(getNama);
        nimMahasiswa.setText(getNim);
    }

    private void updateMahasiswa(Mahasiswa mahasiswa){
        final String Key = getIntent().getExtras().getString("key");
        database.child("mahasiswa").child(Key).setValue(mahasiswa).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                nmMahasiswa.setText("");
                nimMahasiswa.setText("");
                Snackbar.make(findViewById(R.id.act_update), "Data diupdate", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
