package com.example.stage

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.util.Log

class BasketService :Service() {
    var basketList : ArrayList<Bundle> = arrayListOf()
    val binder = LocalBinder()

    inner class LocalBinder: Binder(){
        fun getService() : BasketService = this@BasketService
    }

    fun setBasket(bundle: Bundle){
        basketList.add(bundle)
    }

    fun resetBasket(){
        basketList.clear()
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d("service","onbind")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("service","onUnbind")
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("service","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("service","onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("service","onDestroy")
        super.onDestroy()
    }
}