package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R


class RecipeRVAdapter (var context: Context, var basketList: ArrayList<ArrayList<String>>):
    RecyclerView.Adapter<RecipeRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeRVAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: RecipeRVAdapter.Holder, position: Int) {
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

        fun bind(basketList: ArrayList<String>) {
            recipeMenuName?.text = basketList[1]
            recipeMenuNum?.text = basketList[3]
            recipeMenuCost?.text = basketList[2]
            recipeTotalCost?.text = (basketList[2].toInt() * basketList[3].toInt()).toString()
        }
    }
}