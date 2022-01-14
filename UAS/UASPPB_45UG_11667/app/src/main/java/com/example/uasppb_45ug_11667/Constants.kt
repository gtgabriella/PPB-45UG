package com.example.uasppb_45ug_11667

object Constants { //db name
    const val DB_NAME ="DBRestoran"
    const val DB_VERSION = 1
    const val TABLE_NAME = "TabelMenu"
    const val id = "id"
    const val image = "image"
    const val nama = "nama"
    const val kategori = "kategori"
    const val harga = "harga"
    const val keterangan = "keterangan"


//create table query

    const val CREATE_TABLE = (
            "CREATE TABLE " + TABLE_NAME + "("
                    + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + image + " TEXT,"
                    + nama + " TEXT,"
                    + kategori + " TEXT,"
                    + harga + " INTEGER,"
                    + keterangan + " TEXT"
                    + ")"
    )
}