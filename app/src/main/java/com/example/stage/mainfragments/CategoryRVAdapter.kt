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

class CategoryRVAdapter(var context: Context, categoryList : ArrayList<Category>):

    RecyclerView.Adapter<CategoryRVAdapter.Holder>() {
    var myCategoryList = categoryList
    var isChecked = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myCategoryList,position)
    }

    override fun getItemCount(): Int {
        return myCategoryList.size
    }

    fun listChanged(newCategoryList: ArrayList<Category>){
        myCategoryList.clear()
        myCategoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }


    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        var categoryName = itemView?.findViewById<TextView>(R.id.category_name)
        var categoryEnglishName = itemView?.findViewById<TextView>(R.id.category_english_name)
        var categoryImg = itemView?.findViewById<ImageView>(R.id.category_image)

        fun bind (category: ArrayList<Category>, position: Int){

            categoryName?.text = category[position].categoryName
            categoryEnglishName?.text = category[position].categoryEnglishName
            val resourceId = context.resources.getIdentifier(category[position].photo, "drawable", context.packageName)
            categoryImg?.setImageResource(resourceId)

            itemView.setOnClickListener {
                // 아이템 위치를 전달
                var bundle = Bundle()
                var menuFragment = MenuFragment()
                bundle.putInt("title",position)
                bundle.putBoolean("isChecked",isChecked)
                bundle.putString("categoryName",category[position].categoryName)
                menuFragment.arguments = bundle
                var mainActivity = context as MainActivity
                mainActivity.addFragment(menuFragment)
            }
        }
    }
}