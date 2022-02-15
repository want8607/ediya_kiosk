package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stage.startfragments.StartFragment


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startpage)
        supportFragmentManager.beginTransaction().replace(R.id.start_fragment_container_view,
            StartFragment()
        ).commit()
    }


}

