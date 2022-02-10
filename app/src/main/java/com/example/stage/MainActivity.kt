package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startpage)
        supportFragmentManager.beginTransaction().add(R.id.start_fragment_container_view,StartFragment()).addToBackStack(null).commit()
    }


}

