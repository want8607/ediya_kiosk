package com.example.stage

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.from
import java.util.Date.from

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

    var channelId = "my_channel"
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: NotificationCompat.Builder
    fun createNotificationChannel(){
            val name = "MyNotification"
            val descriptionText = "Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
    }

    fun notifyBasket(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
            builder = NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_order_coffee_icon)
                .setContentTitle("장바구니")
                .setContentText("성공")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            startForeground(1,builder.build())
        }
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