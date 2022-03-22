package com.example.stage

import android.app.Service.STOP_FOREGROUND_REMOVE
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.example.stage.ServerConnection.AccountApi
import com.example.stage.ServerConnection.CategoryApi
import com.example.stage.ServerConnection.RetrofitClient
import com.example.stage.database.DatabaseControl
import com.example.stage.database.DatabaseHelper
import com.example.stage.mainfragments.BasketFragment
import com.example.stage.mainfragments.CategoryFragment
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    lateinit var basketService: BasketService
    lateinit var connection : ServiceConnection
    lateinit var userId : String
    lateinit var loginFlag : String
    lateinit var settingFlag :String
    lateinit var localeHelper: LocaleHelper
    lateinit var applang : String
    lateinit var appMode : String
    lateinit var retrofit : Retrofit
    lateinit var requestCategoryApi : CategoryApi
    lateinit var databaseHelper: DatabaseHelper
    lateinit var databaseControl: DatabaseControl
    lateinit var readableDb: SQLiteDatabase
    lateinit var writableDb: SQLiteDatabase
    lateinit var menuLists : ArrayList<ArrayList<ArrayList<String>>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginFlag = "false"
        settingFlag = "false"
        userId = intent.getStringExtra("id").toString()

        //레트로핏
        retrofit = RetrofitClient.initRetrofit()
        requestCategoryApi = retrofit.create(CategoryApi::class.java)
        databaseHelper = DatabaseHelper(this,"ediya.db",null,1)
        databaseControl = DatabaseControl()
        readableDb = databaseHelper.readableDatabase
        writableDb = databaseHelper.writableDatabase
        menuLists = arrayListOf()

        //window설정
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = getColor(R.color.title_background_color)

        //상태바 설정
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        }else if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        }
        setContentView(R.layout.activity_mainpage)

        //초기 프래그먼트 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,CategoryFragment()).commit()
        }

        //서비스 연결
        connection = object : ServiceConnection{

                override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
                    var binder = service as BasketService.LocalBinder
                    basketService = binder.getService()
                }
                override fun onServiceDisconnected(p0: ComponentName?) {
                }
            }
        Intent(this, BasketService::class.java).also { intent ->
            bindService(intent, connection, BIND_AUTO_CREATE)
        }
    }

    fun openBasket(){
        var basketFragment = BasketFragment()
        var bundle = Bundle()
        bundle.putParcelableArrayList("basketList",basketService.basketList)
        basketFragment.arguments = bundle
        addFragment(basketFragment,"basket")
    }

    fun removeFragment(fragment: Fragment,flag: String){
        supportFragmentManager.beginTransaction().remove(fragment).commit()
        supportFragmentManager.popBackStack(flag,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun addFragment(fragment: Fragment,flag:String){
        supportFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,fragment).addToBackStack(flag).commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d("message", "액티비티 onStart")
    }

    override fun onRestart() {
        super.onRestart()
        if(basketService.notificationFlag=="true") {
            basketService.stopForeground(STOP_FOREGROUND_REMOVE)
        }
        basketService.notificationFlag="false"
        Log.d("message","액티비티 onRestart")
    }

    override fun onStop() {
        super.onStop()
        if((loginFlag=="false")and(settingFlag =="false")) {
            var intent = Intent(this, BasketService::class.java)
            startForegroundService(intent)
        }
        Log.d("message","액티비티 onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("message","액티비티 onDestroy")
    }

    override fun attachBaseContext(newBase: Context) {
        var pref = PreferenceManager.getDefaultSharedPreferences(newBase)
        var lang = pref.getString("app_language","한국어")
        var mode = pref.getString("app_mode","기본 모드")
        Log.d("dd",lang!!)
        localeHelper = LocaleHelper()
        if(lang.equals("Korean") || lang.equals("한국어")){
            super.attachBaseContext(localeHelper.updateLocale(newBase,"ko"))
            applang = "ko"
        }else{
            super.attachBaseContext(localeHelper.updateLocale(newBase,"en"))
            applang = "en"
        }

        if(mode.equals("기본 모드") || mode.equals("Light Mode")){
            appMode = "기본 모드"
        }else if(mode.equals("다크 모드") || mode.equals("Dark Mode")){
            appMode = "다크 모드"
        }else{
            appMode = "시스템 기본값"
        }

    }
}
