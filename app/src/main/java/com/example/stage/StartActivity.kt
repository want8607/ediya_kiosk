package com.example.stage

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.stage.ServerConnection.AccountApi
import com.example.stage.ServerConnection.RetrofitClient
import com.example.stage.database.DatabaseControl
import com.example.stage.startfragments.StartFragment
import retrofit2.Retrofit

class StartActivity : AppCompatActivity() {


    lateinit var localeHelper: LocaleHelper
    lateinit var retrofit: Retrofit
    lateinit var requestAccountApi : AccountApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = getColor(R.color.start_page_blue_color)
        setContentView(R.layout.activity_startpage)
        supportFragmentManager.beginTransaction().replace(R.id.start_fragment_container_view,
            StartFragment()
        ).commit()

        //레트로핏
        retrofit = RetrofitClient.initRetrofit()
        requestAccountApi = retrofit.create(AccountApi::class.java)
    }

    override fun attachBaseContext(newBase: Context) {
        var pref = newBase.getSharedPreferences("com.example.stage_preferences",MODE_PRIVATE)
        var lang = pref.getString("app_language","Korean")!!
        localeHelper = LocaleHelper()
        if(lang.equals("Korean") || lang.equals("한국어")){
            super.attachBaseContext(localeHelper.updateLocale(newBase,"ko"))
        }else{
            super.attachBaseContext(localeHelper.updateLocale(newBase,"en"))
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("message", "onRestart")
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
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("message", "onDestroy")
    }

}

