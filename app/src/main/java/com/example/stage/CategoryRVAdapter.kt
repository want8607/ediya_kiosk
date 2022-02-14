package com.example.stage
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryRVAdapter(var context: Context, var categoryList : ArrayList<Category>) :
    RecyclerView.Adapter<CategoryRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun listChanged(newCategoryList: ArrayList<Category>){

        for (i in newCategoryList.indices){
            Log.d("start", newCategoryList[i].categoryName)
        }
        categoryList.clear()
        categoryList.addAll(newCategoryList)
        for (i in newCategoryList.indices){
            Log.d("addAll", newCategoryList[i].categoryName)
        }
        notifyDataSetChanged()
        for (i in newCategoryList.indices){
            Log.d("changedNew", newCategoryList[i].categoryName)
        }
        for (i in newCategoryList.indices){
            Log.d("chageedOri", categoryList[i].categoryName)
        }
    }


    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        var categoryName = itemView?.findViewById<TextView>(R.id.category_name)
        var categoryEnglishName = itemView?.findViewById<TextView>(R.id.category_english_name)
        var categoryImg = itemView?.findViewById<ImageView>(R.id.category_image)

        fun bind (category: Category){

            categoryName?.text = category.categoryName
            categoryEnglishName?.text = category.categoryEnglishName
            val resourceId = context.resources.getIdentifier(category.photo, "drawable", context.packageName)
            categoryImg?.setImageResource(resourceId)
        }
    }
}