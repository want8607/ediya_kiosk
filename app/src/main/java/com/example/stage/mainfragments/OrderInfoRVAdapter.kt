package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R
import com.example.stage.mainInterface.OrderInfoItemClick

class OrderInfoRVAdapter (var context: Context, var orderInfo: ArrayList<Bundle>, var orderInfoItemClick: OrderInfoItemClick):
    RecyclerView.Adapter<OrderInfoRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderInfoRVAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_info_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: OrderInfoRVAdapter.Holder, position: Int) {
        holder.bind(orderInfo,position)
    }

    override fun getItemCount(): Int {
        return orderInfo.size
    }

    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var orderInfoNum = itemView?.findViewById<TextView>(R.id.order_info_order_num)
        var orderInfoDate = itemView?.findViewById<TextView>(R.id.order_info_date)
        var orderInfoItem = itemView?.findViewById<ConstraintLayout>(R.id.order_info_item)

        fun bind(orderInfo: ArrayList<Bundle>, position: Int) {
            orderInfoNum?.text = (orderInfo[position].getInt("orderNumber")).toString()+"번째 메뉴로 준비중입니다.\n전자영수증을 확인할 수 있습니다."
            orderInfoDate?.text = orderInfo[position].getString("paymentTime")
            orderInfoItem?.setOnClickListener {
                orderInfoItemClick.onclick(RecipeDialogFragment(),position)
            }
        }
    }
}