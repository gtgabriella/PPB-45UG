package com.example.uasppb_45ug_11667

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.example.uasppb_45ug_11667.databinding.ActivityMainBinding
import com.example.uasppb_45ug_11667.databinding.ActivityRecordDetailBinding

class RecordDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordDetailBinding
    private var actionBar: ActionBar? = null

    private var dbHelper:DBHelper?=null
    private var recordId:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ACTION BAR
        actionBar = supportActionBar
        actionBar!!.title = "Detail Menu"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        dbHelper = DBHelper(this)

        val intent = intent
        recordId = intent.getStringExtra("RECORD_ID")
//        Log.e("IDGet", recordId.toString())

        showRecordDetails()
    }

    @SuppressLint("Range")
    private fun showRecordDetails() {
        val selectQuery = "SELECT * FROM ${Constants.TABLE_NAME} WHERE ${Constants.id} = \"$recordId\""
//        Log.e("ID", recordId.toString())

        val db = dbHelper!!.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val id = 0 + cursor.getInt(cursor.getColumnIndex(Constants.id))
                val image = "" + cursor.getString(cursor.getColumnIndex(Constants.image))
                val nama = "" + cursor.getString(cursor.getColumnIndex(Constants.nama))
                val kategori = "" + cursor.getString(cursor.getColumnIndex(Constants.kategori))
                val harga = 0 + cursor.getInt(cursor.getColumnIndex(Constants.harga))
                val keterangan = "" + cursor.getString(cursor.getColumnIndex(Constants.keterangan))

                binding.edNama.text = nama
                binding.edKategori.text = kategori
                binding.edHarga.text = harga.toString()
                binding.edKeterangan.text = keterangan

                if (image == "null"){
                    binding.edImage.setImageResource(R.drawable.ic_fd_black)
                } else {
                    binding.edImage.setImageURI(Uri.parse(image))
                }
            } while (cursor.moveToNext())
        }
        db.close()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}