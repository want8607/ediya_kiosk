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
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.from
import java.util.Date.from
//날리지 않으면 onStop불러짐
class BasketService :Service() {
    lateinit var notificationManager: NotificationManager
    var basketList : ArrayList<Bundle> = arrayListOf()
    val binder = LocalBinder()
    var totalCost = 0
    var totalMenuNum = 0

    fun updateTotalMenuNum() {
        totalMenuNum = 0
        totalCost = 0
        for (i in basketList.indices) {
            totalMenuNum += basketList[i].getString("basketMenuNum")?.toInt()!!
        }
        for (j in basketList.indices) {
            totalCost += basketList[j].getString("basketTotalCost")?.toInt()!!
        }
    }

    fun setBasket(bundle: Bundle){
        basketList.add(bundle)
    }

    fun resetBasket(){
        basketList.clear()
    }
    
    //알림창 설정
    var channelId = "my_channel"

    inner class LocalBinder: Binder(){
        fun getService() : BasketService = this@BasketService
    }
    fun createNotificationChannel(){
            val name = "MyNotification"
            val descriptionText = "Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
    }

    fun notifyBasket(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            createNotificationChannel()

            var context = "총 "+"$totalMenuNum"+"개 "+"$totalCost"+"원"
            var style = NotificationCompat.InboxStyle()

            for (i in basketList.indices){
                style.addLine(
                    basketList[i].getString("basketName")+" "+basketList[i].getString("basketMenuNum")+"개 "
                            +basketList[i].getString("basketTotalCost")+"원"
                )
            }
            style.addLine(context)

            var builder: NotificationCompat.Builder = NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_order_coffee_icon)
                .setContentTitle("이디야 장바구니")
                .setContentText(context)
                .setStyle(style)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            startForeground(100,builder.build())
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d("service","onbind")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("service","onUnbind")
        return super.onUnbind(intent)
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("service","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("service","onStartCommand")
        notifyBasket()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d("service","onDestroy")
        super.onDestroy()
    }
}