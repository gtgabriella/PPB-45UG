package com.example.uasppb_45ug_11667

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.uasppb_45ug_11667.databinding.ActivityAddRecordBinding
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class AddRecord : AppCompatActivity() {
    private lateinit var binding: ActivityAddRecordBinding

    private val CAMERA_REQUEST_CODE = 100
    private val STORAGE_REQUEST_CODE = 101
    private val IMAGE_PICK_CAMERA_CODE = 102
    private val IMAGE_PICK_GALERY_CODE = 103
    private lateinit var cameraPermission: Array<String>
    private lateinit var storagePermission: Array<String>
    private var imageUri:Uri? = null
    private var id:String? = null
    private var nama:String? = ""
    private var kategori:String? = ""
    private var harga:Int? = 0
    private var keterangan:String? = ""

    private var isEditMode = false

//    val dbHelper:DBHelper

    private var actionBar:ActionBar? = null

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ACTION BAR
        actionBar = supportActionBar
        actionBar!!.title = "Tambah Data Menu"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        val intent = intent
        isEditMode = intent.getBooleanExtra("isEditMode", false)

        if (isEditMode) {
            actionBar!!.title = "Edit Menu"
            id = intent.getStringExtra("id")
            Log.e("IDhehe", id.toString())
            imageUri = Uri.parse(intent.getStringExtra("image"))
            Log.e("imagehehe", imageUri.toString())
            nama = intent.getStringExtra("nama")
            Log.e("namamama", nama.toString())
            kategori = intent.getStringExtra("kategori")
            harga = intent.getIntExtra("harga", harga!!)
            keterangan = intent.getStringExtra("keterangan")
            if (imageUri.toString() == "null"){
                binding.edImage.setImageResource(R.drawable.ic_fd_black)
            } else {
                binding.edImage.setImageURI(imageUri)
            }
            binding.edNama.setText(nama)
            binding.edKategori.setText(kategori)
            binding.edHarga.setText(harga.toString())
            binding.edKeterangan.setText(keterangan)
        }else{
            actionBar!!.title = "Add Menu"
        }

        dbHelper = DBHelper(this)

        cameraPermission = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        storagePermission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        binding.edImage.setOnClickListener {
            imagePickDialog()
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }

        binding.saveBtn.setOnClickListener {
//            startActivity(Intent(this, CRUDMenu::class.java))
            inputData()
        }
    }

    private fun inputData() {
        nama = "" + binding.edNama.text.toString().trim()
        kategori = "" + binding.edKategori.text.toString().trim()
        harga = binding.edHarga.text.toString().toInt()
        keterangan = "" + binding.edKeterangan.text.toString().trim()

        if (isEditMode){
            dbHelper?.updateRecord(
                "$id",
                "$imageUri",
                "$nama",
                "$kategori",
                0+ harga!!,
                "$keterangan"
            )
            Log.e("ID AddRecord", id.toString())
            Log.e("harga", harga.toString())
            Log.e("nama", nama.toString())
            Toast.makeText(this, "Data berhasil diupdate!", Toast.LENGTH_SHORT).show()
        } else {
            val id = dbHelper.insertRecord(
                ""+imageUri,
                ""+nama,
                ""+kategori,
                0+ harga!!,
                ""+keterangan
            )
            Toast.makeText(this, "Data telah diinput!", Toast.LENGTH_SHORT).show()
        }
        startActivity(Intent(this, CRUDMenu::class.java))
    }

    private fun imagePickDialog() {
        val options = arrayOf("Camera", "Gallery")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick Image From")
        builder.setItems(options){dialog, which->
            if (which == 0){
                if (!checkCameraPermission()){
                    requestCameraPermission()
                } else {
                    pickFromCamera()
                }
            } else {
                if(!checkStoragePermission()){
                    requestStoragePermission()
                } else {
                    pickFromGallery()
                }
            }
        }
        builder.show()
    }

    private fun pickFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(
            galleryIntent,
            IMAGE_PICK_GALERY_CODE
        )
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE)
    }

    private fun checkStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun pickFromCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Image Title")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Description")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(
            cameraIntent,
            IMAGE_PICK_CAMERA_CODE
        )
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE)
    }

    private fun checkCameraPermission(): Boolean {
        val results = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        val results1 = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        return results && results1
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if(grantResults.isNotEmpty()){
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    if (cameraAccepted&&storageAccepted){
                        pickFromCamera()
                    } else {
                        Toast.makeText(this, "Camera and Storage permisson are required", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            STORAGE_REQUEST_CODE->{
                val storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (storageAccepted){
                    pickFromGallery()
                } else {
                    Toast.makeText(this, "Storage permisson is required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == IMAGE_PICK_GALERY_CODE){
                CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            } else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(data!!.data)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK){
                    val resultUri = result.uri
                    imageUri = resultUri
                    binding.edImage.setImageURI(resultUri)
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    val error = result.error
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show()
                }
            }

        super.onActivityResult(requestCode, resultCode, data)
        }
    }
}