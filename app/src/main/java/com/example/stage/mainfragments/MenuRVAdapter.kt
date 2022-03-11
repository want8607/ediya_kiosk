package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R

class MenuRVAdapter(var context: Context, var menuList : ArrayList<ArrayList<String>>) :
    RecyclerView.Adapter<MenuRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(menuList,position)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }


    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        var menuName = itemView?.findViewById<TextView>(R.id.menu_name)
        var menuEnglishName = itemView?.findViewById<TextView>(R.id.menu_english_name)
        var menuCost = itemView?.findViewById<TextView>(R.id.menu_cost)
        var menuImg = itemView?.findViewById<ImageView>(R.id.menu_image)

        fun bind (menu: ArrayList<ArrayList<String>>,position: Int){

            menuName?.text = menu[position][1]
            menuEnglishName?.text = menu[position][2]
            menuCost?.text = menu[position][3]
            var resourceId = context.resources.getIdentifier(menu[position][4], "drawable", context.packageName)
            menuImg?.setImageResource(resourceId)
            itemView.setOnClickListener {
                var mainActivity = context as MainActivity
                var selectMenuFragment = SelectMenuFragment()
                var bundle = Bundle()
                //클래스를 넘겨야함
                bundle.putString("menuName",menu[position][1])
                bundle.putString("menuEnglishName",menu[position][2])
                bundle.putString("menuCost",menu[position][3])
                bundle.putString("menuImg",menu[position][4])
                selectMenuFragment.arguments = bundle
                mainActivity.addFragment(selectMenuFragment,"selectMenu")
            }
        }
    }

}