package com.example.stage

import android.app.Service.STOP_FOREGROUND_REMOVE
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stage.mainfragments.BasketFragment
import com.example.stage.mainfragments.CategoryFragment
import com.example.stage.mainfragments.OrderInfoFragment


class MainActivity : AppCompatActivity() {

    lateinit var  basketService: BasketService
    var notificationFlag = false
    var orderStorage : ArrayList<Bundle> = arrayListOf() // 주문번호, 주문시간, 장바구니정보 3
    var orderNumber = 1
    val connection = object : ServiceConnection{

        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            var binder = service as BasketService.LocalBinder
            basketService = binder.getService()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_mainpage)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = getColor(R.color.white)
    }


    fun openBasket(){
        var basketFragment = BasketFragment()
        var bundle = Bundle()
        bundle.putParcelableArrayList("basketList",basketService.basketList)
        basketFragment.arguments = bundle
        addFragment(basketFragment,"basket")
    }

    fun openOrderInfo(){
        var orderInfoFragment = OrderInfoFragment()
        var bundle = Bundle()
        bundle.putParcelableArrayList("orderStorage",orderStorage)
        orderInfoFragment.arguments = bundle
        addFragment(orderInfoFragment,"orderInfo")
    }

    fun removeFragment(fragment: Fragment,flag: String){
        supportFragmentManager.beginTransaction().remove(fragment).commit()
        supportFragmentManager.popBackStack(flag,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun addFragment(fragment: Fragment,flag:String){
        supportFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,fragment).addToBackStack(flag).commit()
    }

    fun addOrderInfo(bundle: Bundle){
        orderStorage.add(bundle) //2
    }

    override fun onStart() {
        super.onStart()
        Log.d("message", "액티비티 onStart")
        Intent(this, BasketService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        if (notificationFlag) {
            basketService.stopForeground(STOP_FOREGROUND_REMOVE)
            notificationFlag = false
        }
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("message","액티비티 onRestart")
    }

    override fun onStop() {
        super.onStop()
        var intent = Intent(this,BasketService::class.java)
        if(!notificationFlag) {
            startForegroundService(intent)
            notificationFlag = true
        }
        Log.d("message","액티비티 onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("message","액티비티 onDestroy")
        unbindService(connection)
    }
}
