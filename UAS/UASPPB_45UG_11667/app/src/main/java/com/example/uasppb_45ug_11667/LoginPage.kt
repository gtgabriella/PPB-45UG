package com.example.uasppb_45ug_11667

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.uasppb_45ug_11667.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)//BINDING
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener {
            if (binding.username.text.toString().trim() == "admin" && binding.password.text.toString().trim() == "abc123"){
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, CRUDMenu::class.java))
            } else if (binding.username.text.toString().trim() != "admin" && binding.password.text.toString().trim() == "abc123") {
                Toast.makeText(this, "Username salah!", Toast.LENGTH_SHORT).show()
            } else if (binding.username.text.toString().trim() == "admin" && binding.password.text.toString().trim() != "abc123") {
                Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Username dan Password salah!", Toast.LENGTH_SHORT).show()
            }
        }

        //ACTION BAR
        actionBar = supportActionBar
        actionBar!!.title = "Halaman Admin"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}