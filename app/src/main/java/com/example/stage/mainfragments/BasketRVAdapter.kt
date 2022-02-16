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

class BasketRVAdapter(var context: Context, var basketList: MutableList<MutableList<String?>>):
    RecyclerView.Adapter<BasketRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_recycler_view_item, parent, false)
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

        fun bind (basketList:MutableList<MutableList<String?>>, position: Int){

            basketName?.text = basketList[position][0]
            basketEnglishName?.text = basketList[position][1]
            var optionText = "${basketList[position][2]}|" +
                    "${basketList[position][3]}|" +
                    "${basketList[position][4]}\n" +
                    "${basketList[position][5]}|" +
                    "${basketList[position][6]}"
            basketOptions?.text = optionText
            basketMenuNum?.text = basketList[position][7]
            basketMenuCost?.text = basketList[position][8]
            basketTotalCost?.text = basketList[position][9]
            val resourceId = context.resources.getIdentifier(basketList[position][10], "drawable", context.packageName)
            basketImg?.setImageResource(resourceId)

            basketOptionChangeButton?.setOnClickListener {
                // 옵션변경사항 전달
                var bundle = Bundle()
            }
        }
    }
}