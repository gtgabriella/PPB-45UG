package com.example.uasppb_45ug_11667

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class AdapterDaftarMenu(): RecyclerView.Adapter<AdapterDaftarMenu.HolderRecord>(){
    private var context:Context?=null
    private var recordList:ArrayList<ModelRecord>?=null

    lateinit var dbHelper:DBHelper

    constructor(context: Context?, recordList: ArrayList<ModelRecord>?) : this() {
        this.context = context
        this.recordList = recordList

        dbHelper = DBHelper(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        return HolderRecord(
            LayoutInflater.from(context).inflate(R.layout.row_menu_pelanggan, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        val model = recordList!!.get(position)

        val id = model.id
        val image = model.image
        val nama = model.nama
        val kategori = model.kategori
        val harga = model.harga

        holder.edNama.text  = nama
        holder.edKategori.text  = kategori
        holder.edHarga.text  = harga.toString()

        if (image == "null"){
            holder.edImage.setImageResource(R.drawable.ic_fd_black)
        } else {
            holder.edImage.setImageURI(Uri.parse(image))
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, RecordDetailActivity::class.java)
            intent.putExtra("RECORD_ID", id.toString())
//            Log.e("ID Sent: ", id.toString())
            context!!.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return recordList!!.size
    }

    inner class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView){
        var edImage:CircleImageView = itemView.findViewById(R.id.edImage)
        var edNama:TextView = itemView.findViewById(R.id.edNama)
        var edKategori:TextView = itemView.findViewById(R.id.edKategori)
        var edHarga:TextView = itemView.findViewById(R.id.edHarga)
    }

}