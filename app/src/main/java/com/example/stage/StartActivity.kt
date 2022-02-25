package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.stage.startfragments.StartFragment


class StartActivity : AppCompatActivity() {
     var start : String = "1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startpage)
        supportFragmentManager.beginTransaction().replace(R.id.start_fragment_container_view,
            StartFragment()
        ).commit()
        Log.d("스타트", start)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("스타트","2")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("message", "onRestart")
        start = "2"
        Log.d("스타트", start)
    }
    override fun onStart() {
        super.onStart()
        Log.d("message", "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("message", "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("message", "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("message", "onStop")
//        if(savedInstanceState != null){
//            start = savedInstanceState.getString("스타트")!!
//        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("message", "onDestroy")
    }
}

