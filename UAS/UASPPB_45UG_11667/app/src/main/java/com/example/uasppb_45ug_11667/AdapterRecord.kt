package com.example.uasppb_45ug_11667

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class AdapterRecord(): RecyclerView.Adapter<AdapterRecord.HolderRecord>(){
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
            LayoutInflater.from(context).inflate(R.layout.row_menu, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        val model = recordList!!.get(position)

        val id = model.id
        val image = model.image
        val nama = model.nama
        val kategori = model.kategori
        val harga = model.harga
        val keterangan = model.keterangan

        holder.edNama.text  = nama
        holder.edKategori.text  = kategori
        holder.edHarga.text  = harga.toString()
        holder.edKeterangan.text  = keterangan

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

        holder.moreBtn.setOnClickListener {
            showMoreOption(
                position,
                id,
                image,
                nama,
                kategori,
                harga,
                keterangan
            )
        }

    }

    private fun showMoreOption(position: Int, id: Int, image: String, nama: String, kategori: String, harga: Int, keterangan: String) {
        val options = arrayOf("Edit", "Delete")
        val dialog:AlertDialog.Builder =  AlertDialog.Builder(context!!)
        dialog.setItems(options){dialog, which->
            if (which == 0){
                val intent = Intent(context, AddRecord::class.java)
                    intent.putExtra("id", id.toString())
                    intent.putExtra("image", image)
                    intent.putExtra("nama", nama)
                    intent.putExtra("kategori", kategori)
                    intent.putExtra("harga", harga)
                    intent.putExtra("keterangan", keterangan)
                    intent.putExtra("isEditMode", true)
                context!!.startActivity(intent)
                Log.e("ID SentAdapter", id.toString())
                Log.e("Image SentAdapter", image)
                Log.e("Nama SentAdapter", nama)
            } else {
                dbHelper.deleteRecords(id.toString())
                (context as CRUDMenu)!!.onResume()
            }
        }
        dialog.show()

    }

    override fun getItemCount(): Int {
        return recordList!!.size
    }

    inner class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView){
        var edImage:CircleImageView = itemView.findViewById(R.id.edImage)
        var edNama:TextView = itemView.findViewById(R.id.edNama)
        var edKategori:TextView = itemView.findViewById(R.id.edKategori)
        var edHarga:TextView = itemView.findViewById(R.id.edHarga)
        var edKeterangan:TextView = itemView.findViewById(R.id.edKeterangan)
        var moreBtn:ImageButton = itemView.findViewById(R.id.moreBtn)
    }

}