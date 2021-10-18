package com.example.crudlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Implement Methods, Create Constructor
public class BantuDatabase extends SQLiteOpenHelper {


    private static final String TABEL_TOKOH = "tabel_tokoh";
    private static final String KODE = "kode";
    private static final String NM_TOKOH = "nm_tokoh";
    private static final String DATABASE_TOKOH = "db_tokoh";


    public BantuDatabase(Context context) {
        super(context, DATABASE_TOKOH, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String nama_tabel = "create table " + TABEL_TOKOH + "(" + KODE + " integer primary key autoincrement, " + NM_TOKOH + " text)";
        //  tabel buat field
        db.execSQL(nama_tabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean tambahData(String namaTokoh){

        SQLiteDatabase db = this.getWritableDatabase(); //menyiapkan database agar bisa ditulisi
        //penampungan wadah dalam memori menggunakan class Content Values
        ContentValues contentValues = new ContentValues();
        contentValues.put(NM_TOKOH, namaTokoh);

        long hasil = db.insert(TABEL_TOKOH, null, contentValues);
        return hasil != -1;
    }

    public Cursor tampilTokoh(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + TABEL_TOKOH;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
}
