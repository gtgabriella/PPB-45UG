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
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String POSITION = "position";
    private static final String MOTTO = "motto";


    public BantuDatabase(Context context) {
        super(context, DATABASE_TOKOH, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String nama_tabel = "create table " + TABEL_TOKOH + "(" + KODE + " integer primary key autoincrement, " + NM_TOKOH + " text, " + DATE_OF_BIRTH + " text, " + POSITION + " text, " + MOTTO + " text)";
        //  tabel buat field
        db.execSQL(nama_tabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean tambahData(String name, String dateOfBirth, String position, String motto){

        SQLiteDatabase db = this.getWritableDatabase(); //menyiapkan database agar bisa ditulisi
        //penampungan wadah dalam memori menggunakan class Content Values
        ContentValues contentValues = new ContentValues();
        contentValues.put(NM_TOKOH, name);
        contentValues.put(DATE_OF_BIRTH, dateOfBirth);
        contentValues.put(POSITION, position);
        contentValues.put(MOTTO, motto);

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
