package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R


class RecipeRVAdapter (var context: Context, var basketList: ArrayList<Bundle>):
    RecyclerView.Adapter<RecipeRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeRVAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: RecipeRVAdapter.Holder, position: Int) {
        holder.bind(basketList,position)
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

        fun bind(basketList: ArrayList<Bundle>, position: Int) {
            recipeMenuName?.text = basketList[position].getString("basketName")
            recipeMenuNum?.text = basketList[position].getString("basketMenuNum")
            recipeMenuCost?.text = basketList[position].getString("basketMenuCost")
            recipeTotalCost?.text = basketList[position].getString("basketTotalCost")
        }
    }
}