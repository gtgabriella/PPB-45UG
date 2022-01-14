package com.example.uasppb_45ug_11667

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper (context: Context?): SQLiteOpenHelper (
    context,
    Constants.DB_NAME,
    null,
    Constants.DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Constants.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME)
        onCreate(db)
    }

    //insert record to db
    fun insertRecord(
        image: String?,
        nama: String?,
        kategori: String?,
        harga: Int?,
        keterangan: String?
    ): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Constants.image, image)
        values.put(Constants.nama, nama)
        values.put(Constants.kategori, kategori)
        values.put(Constants.harga, harga)
        values.put(Constants.keterangan, keterangan)
        val id = db.insert(Constants.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun updateRecord(
        id:String,
        image: String?,
        nama: String?,
        kategori: String?,
        harga: Int?,
        keterangan: String?
    ):Long{
        Log.e("Hello", "masuk gak")
        Log.e("ID", id)
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(Constants.image, image)
        values.put(Constants.nama, nama)
        values.put(Constants.kategori, kategori)
        values.put(Constants.harga, harga)
        values.put(Constants.keterangan, keterangan)
        Log.e("Hello", "masuk gak2")
        return db.update(Constants.TABLE_NAME, values, Constants.id+"=?", arrayOf(id)).toLong()
        Log.e("IDSent", "hehehe")

    }
    @SuppressLint("Range")
    fun getAllRecords(orderBy:String):ArrayList<ModelRecord>{
        val recordList = ArrayList<ModelRecord>()
        val selectQuery =  "SELECT * FROM ${Constants.TABLE_NAME} ORDER BY $orderBy"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val modelRecord = ModelRecord(
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.id)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.image)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.nama)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.kategori)),
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.harga)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.keterangan))
                    )
                recordList.add(modelRecord)
            } while (cursor.moveToNext())
        }
        db.close()
        return recordList
    }

    @SuppressLint("Range")
    fun searchRecord(query:String):ArrayList<ModelRecord>{
        val recordList = ArrayList<ModelRecord>()
        val selectQuery =  "SELECT * FROM ${Constants.TABLE_NAME} WHERE ${Constants.nama} LIKE '%$query%'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val modelRecord = ModelRecord(
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.id)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.image)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.nama)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.kategori)),
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.harga)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.keterangan))
                )
                recordList.add(modelRecord)
            } while (cursor.moveToNext())
        }
        db.close()
        return recordList
    }

    fun deleteRecords(id:String){
        val db = writableDatabase
        db.delete(Constants.TABLE_NAME, Constants.id+"=?", arrayOf(id))
        db.close()
    }

    fun deleteAllRecords(){
        val db = writableDatabase
        db.execSQL("DELETE FROM ${Constants.TABLE_NAME}")
        db.close()
    }

    @SuppressLint("Range")
    fun getAllMakanan(orderBy:String):ArrayList<ModelRecord>{
        val recordList = ArrayList<ModelRecord>()
        val selectQuery =  "SELECT * FROM ${Constants.TABLE_NAME} WHERE ${Constants.kategori} = 'makanan' ORDER BY $orderBy"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val modelRecord = ModelRecord(
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.id)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.image)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.nama)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.kategori)),
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.harga)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.keterangan))
                )
                recordList.add(modelRecord)
            } while (cursor.moveToNext())
        }
        db.close()
        return recordList
    }

    @SuppressLint("Range")
    fun getAllMinuman(orderBy:String):ArrayList<ModelRecord>{
        val recordList = ArrayList<ModelRecord>()
        val selectQuery =  "SELECT * FROM ${Constants.TABLE_NAME} WHERE ${Constants.kategori} = 'minuman' ORDER BY $orderBy"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val modelRecord = ModelRecord(
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.id)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.image)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.nama)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.kategori)),
                    0 + cursor.getInt(cursor.getColumnIndex(Constants.harga)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.keterangan))
                )
                recordList.add(modelRecord)
            } while (cursor.moveToNext())
        }
        db.close()
        return recordList
    }
}