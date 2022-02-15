package com.example.stage.mainfragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.R

class MenuRVAdapter(var context: Context, menuList : MutableList<Menu>, var itemClick: (Menu)-> Unit) :
    RecyclerView.Adapter<MenuRVAdapter.Holder>() {
    var myMenuList = menuList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_recycler_view_item, parent, false)
        return Holder(view,itemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myMenuList[position])
    }

    override fun getItemCount(): Int {
        return myMenuList.size
    }


    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?, itemClick: (Menu)-> Unit) : RecyclerView.ViewHolder(itemView!!){
        var menuName = itemView?.findViewById<TextView>(R.id.menu_name)
        var menuEnglishName = itemView?.findViewById<TextView>(R.id.menu_english_name)
        var menuCost = itemView?.findViewById<TextView>(R.id.menu_cost)
        var menuImg = itemView?.findViewById<ImageView>(R.id.menu_image)

        fun bind (menu: Menu){

            menuName?.text = menu.menuName
            menuEnglishName?.text = menu.menuEnglishName
            menuCost?.text = menu.cost
            val resourceId = context.resources.getIdentifier(menu.photo, "drawable", context.packageName)
            menuImg?.setImageResource(resourceId)

            itemView.setOnClickListener { itemClick(menu) }
        }
    }

}