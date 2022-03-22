package com.example.stage.mainfragments.mainRVAdapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stage.R
import com.example.stage.ServerConnection.RetrofitClient

class PaymentRVAdapter(var context: Context, var basketList: ArrayList<Bundle>):
    RecyclerView.Adapter<PaymentRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.payment_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(basketList,position)
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var paymentName = itemView?.findViewById<TextView>(R.id.payment_recyclerview_menu_name)
        var paymentMenuNum = itemView?.findViewById<TextView>(R.id.payment_recyclerview_menu_num)
        var paymentImg = itemView?.findViewById<ImageView>(R.id.payment_recyclerview_img)
        fun bind(basketList: ArrayList<Bundle>, position: Int) {
            paymentName?.text = basketList[position].getString("basketName")
            paymentMenuNum?.text = basketList[position].getString("basketMenuNum")
            Glide.with(context)
                .load(RetrofitClient.initRetrofit().baseUrl().toString()+basketList[position].getString("photo"))
                .into(paymentImg!!)
        }
    }
}