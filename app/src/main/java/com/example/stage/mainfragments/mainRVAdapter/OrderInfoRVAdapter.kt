package com.example.stage.mainfragments.mainRVAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R
import com.example.stage.mainfragments.mainInterface.OrderInfoItemClick
import com.example.stage.mainfragments.maindialog.RecipeDialogFragment

class OrderInfoRVAdapter (var context: Context, var orderInfo: ArrayList<ArrayList<String>>, var orderInfoItemClick: OrderInfoItemClick):
    RecyclerView.Adapter<OrderInfoRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_info_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(orderInfo[position])
    }

    override fun getItemCount(): Int {
        return orderInfo.size
    }

    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var orderInfoNum = itemView?.findViewById<TextView>(R.id.order_info_order_num)
        var orderInfoDate = itemView?.findViewById<TextView>(R.id.order_info_date)
        var orderInfoItem = itemView?.findViewById<ConstraintLayout>(R.id.order_info_item)
        fun bind(orderInfo: ArrayList<String>) {
            orderInfoNum?.text = (orderInfo[0]+" "+context.getString(R.string.order_history_comment)+"\n"+context.getString(R.string.order_history_comment2))
            orderInfoDate?.text = ""
            orderInfoItem?.setOnClickListener {
                orderInfoItemClick.onclick(RecipeDialogFragment(),orderInfo[0].toInt())
            }
        }
    }
}