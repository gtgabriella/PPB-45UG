package com.example.uasppb_45ug_11667

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import com.example.uasppb_45ug_11667.databinding.ActivityCrudmenuBinding
import com.example.uasppb_45ug_11667.databinding.ActivityMainBinding

class CRUDMenu : AppCompatActivity() {
    private lateinit var binding: ActivityCrudmenuBinding
    private var actionBar: ActionBar? = null

    lateinit var dbHelper:DBHelper
    private val BY_ID = "${Constants.id} DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //BINDING
        binding = ActivityCrudmenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addRecordBtn.setOnClickListener {
            val intent = Intent(this, AddRecord::class.java)
            intent.putExtra("isEditMode", false)
            startActivity(intent)
        }

        dbHelper = DBHelper(this)
        loadRecords()

        //ACTION BAR
        actionBar = supportActionBar
        actionBar!!.title = "Halaman Admin"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun loadRecords() {
        val adapterRecord = AdapterRecord(this, dbHelper.getAllRecords(BY_ID))
        binding.rvCRUDMenu.adapter = adapterRecord
    }

    private fun searchRecords(query: String) {
        val adapterRecord = AdapterRecord(this, dbHelper.searchRecord(query))
        binding.rvCRUDMenu.adapter = adapterRecord
    }

    public override fun onResume() {
        super.onResume()
        loadRecords()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater .inflate(R.menu.menu_main, menu)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_deleteall){
            dbHelper.deleteAllRecords()
            onResume()
        }
        return super.onOptionsItemSelected(item)
    }
}