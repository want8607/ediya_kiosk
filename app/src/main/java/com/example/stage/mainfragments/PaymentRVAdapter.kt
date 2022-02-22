package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R

class PaymentRVAdapter(var context: Context, var basketList: ArrayList<Bundle>):
    RecyclerView.Adapter<PaymentRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentRVAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.payment_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: PaymentRVAdapter.Holder, position: Int) {
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
            val resourceId = context.resources.getIdentifier(
                basketList[position].getString("photo"),
                "drawable",
                context.packageName
            )
            paymentImg?.setImageResource(resourceId)


        }
    }
}