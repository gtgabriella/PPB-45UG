package com.example.uasppb_45ug_11667

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import androidx.core.view.isInvisible
import com.example.uasppb_45ug_11667.databinding.ActivityDaftarMenuBinding

class DaftarMenu : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarMenuBinding
    private var actionBar: ActionBar? = null

    lateinit var dbHelper:DBHelper
    private val BY_ID = "${Constants.id} DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        dbHelper = DBHelper(this)

        loadRecordsMakanan()
        loadRecordsMinuman()

        //ACTION BAR
        actionBar = supportActionBar
        actionBar!!.title = "Daftar Menu"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun searchRecords(query: String) {
        val adapterDaftarMenu = AdapterDaftarMenu(this, dbHelper.searchRecord(query))
        binding.rvCRUDMenuMakanan.adapter = adapterDaftarMenu
    }

    private fun loadRecordsMakanan() {
        val adapterDaftarMenu = AdapterDaftarMenu(this, dbHelper.getAllMakanan(BY_ID))
        binding.rvCRUDMenuMakanan.adapter = adapterDaftarMenu
    }

    private fun loadRecordsMinuman() {
        val adapterDaftarMenu = AdapterDaftarMenu(this, dbHelper.getAllMinuman(BY_ID))
        binding.rvCRUDMenuMinuman.adapter = adapterDaftarMenu
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater .inflate(R.menu.menu_notmain, menu)

        val item = menu!!.findItem(R.id.action_search)
        val searchView = item.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener( object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null){
                    searchRecords(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null){
                    searchRecords(query)
                }
                return true
            }
        }

        )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}