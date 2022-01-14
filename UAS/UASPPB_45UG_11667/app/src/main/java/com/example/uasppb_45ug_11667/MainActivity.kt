package com.example.uasppb_45ug_11667

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uasppb_45ug_11667.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //        BINDING
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //BINDING
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //JANGAN LUPA BINDING
        binding.loginAdminBtn.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
        }

        binding.bukaMenuBtn.setOnClickListener {
            startActivity(Intent(this, DaftarMenu::class.java))
        }


    }
}