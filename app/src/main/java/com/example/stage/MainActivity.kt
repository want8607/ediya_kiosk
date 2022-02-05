package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton


class MainActivity : AppCompatActivity(R.layout.activity_startpage) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSetBtn()
    }

    fun initSetBtn(){
        var startLoginBtn = findViewById<Button>(R.id.start_login_button)
        var startRegistBtn = findViewById<Button>(R.id.start_regist_button)
        var startBackBtn = findViewById<ImageButton>(R.id.start_back_btn)
        startLoginBtn!!.setOnClickListener {
            startBackBtn.setVisibility(View.VISIBLE)
            supportFragmentManager.beginTransaction().replace(R.id.start_fragment_container_view, LoginFragment()).commit()
        }
        startRegistBtn!!.setOnClickListener {
            startBackBtn.setVisibility(View.VISIBLE)
            supportFragmentManager.beginTransaction().replace(R.id.start_fragment_container_view, RegistFragment()).commit()
        }
        startBackBtn!!.setOnClickListener{
            startBackBtn.setVisibility(View.INVISIBLE)
            supportFragmentManager.beginTransaction().replace(R.id.start_fragment_container_view, StartFragment()).commit()
        }
    }

}

