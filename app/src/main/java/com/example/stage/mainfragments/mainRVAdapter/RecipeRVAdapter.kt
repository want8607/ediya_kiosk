package com.example.stage.mainfragments.mainRVAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R
import com.example.stage.ServerConnection.OrderHistoryData


class RecipeRVAdapter (var context: Context, var basketList: ArrayList<OrderHistoryData>):
    RecyclerView.Adapter<RecipeRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(basketList[position])
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var recipeMenuName = itemView?.findViewById<TextView>(R.id.recipe_menu_name)
        var recipeMenuNum = itemView?.findViewById<TextView>(R.id.recipe_menu_num)
        var recipeMenuCost = itemView?.findViewById<TextView>(R.id.recipe_menu_cost)
        var recipeTotalCost = itemView?.findViewById<TextView>(R.id.recipe_menu_totalcost)

        fun bind(orderHistoryData: OrderHistoryData) {
            recipeMenuName?.text = orderHistoryData.name
            recipeMenuNum?.text = orderHistoryData.count.toString()
            recipeMenuCost?.text = (orderHistoryData.sum_price/orderHistoryData.count).toString()
            recipeTotalCost?.text = orderHistoryData.sum_price.toString()
        }
    }
}