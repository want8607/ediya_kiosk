package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R

class BasketRVAdapter(var context: Context, var basketList: ArrayList<Bundle>):
    RecyclerView.Adapter<BasketRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.basket_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(basketList,position)
    }

    override fun getItemCount(): Int {
        return basketList.size
    }


    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        var basketName = itemView?.findViewById<TextView>(R.id.basket_name)
        var basketEnglishName = itemView?.findViewById<TextView>(R.id.basket_english_name)
        var basketOptions = itemView?.findViewById<TextView>(R.id.basket_option_textview)
        var basketOptionChangeButton = itemView?.findViewById<Button>(R.id.basket_option_change_button)
        var basketMenuNum = itemView?.findViewById<TextView>(R.id.basket_menu_num_textview)
        var basketMenuCost = itemView?.findViewById<TextView>(R.id.basket_menu_cost)
        var basketTotalCost = itemView?.findViewById<TextView>(R.id.basket_total_cost)
        var basketImg = itemView?.findViewById<ImageView>(R.id.basket_image)

        fun bind (basketList:ArrayList<Bundle>, position: Int){

            basketName?.text = basketList[position].getString("basketName")
            basketEnglishName?.text = basketList[position].getString("basketEnglishName")
            var optionText = "${basketList[position].getString("basketHotOrIce")}|" +
                    "${basketList[position].getString("basketSize")}|" +
                    "${basketList[position].getString("basketCup")}\n" +
                    "${basketList[position].getString("basketShotNum")}|" +
                    "${basketList[position].getString("basketSyrupNum")}"
            basketOptions?.text = optionText
            basketMenuNum?.text = basketList[position].getString("basketMenuNum")
            basketMenuCost?.text = basketList[position].getString("basketMenuCost")
            basketTotalCost?.text = basketList[position].getString("basketTotalCost")
            val resourceId = context.resources.getIdentifier(basketList[position].getString("photo"), "drawable", context.packageName)
            basketImg?.setImageResource(resourceId)

            basketOptionChangeButton?.setOnClickListener {
                // 옵션변경사항 전달
                var bundle = Bundle()
            }
        }
    }
}