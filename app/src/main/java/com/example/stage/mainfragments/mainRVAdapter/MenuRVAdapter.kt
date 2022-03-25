package com.example.stage.mainfragments.mainRVAdapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.ServerConnection.RetrofitClient
import com.example.stage.mainfragments.SelectMenuFragment

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

            menuName?.text = menu[position][0]
            menuEnglishName?.text = menu[position][1]
            menuCost?.text = menu[position][2]
            Glide.with(context)
                .load(RetrofitClient.initRetrofit().baseUrl().toString()+menu[position][3])
                .override(120,120)
                .into(menuImg!!)
            itemView.setOnClickListener {
                var mainActivity = context as MainActivity
                var selectMenuFragment = SelectMenuFragment()
                var bundle = Bundle()
                //클래스를 넘겨야함
                bundle.putString("menuName",menu[position][0])
                bundle.putString("menuEnglishName",menu[position][1])
                bundle.putString("menuCost",menu[position][2])
                bundle.putString("menuImg",menu[position][3])
                selectMenuFragment.arguments = bundle
                mainActivity.addFragment(selectMenuFragment,"selectMenu")
            }
        }
    }

}