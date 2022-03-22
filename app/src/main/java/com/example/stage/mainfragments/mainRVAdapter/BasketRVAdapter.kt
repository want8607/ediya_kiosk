package com.example.stage.mainfragments.mainRVAdapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stage.R
import com.example.stage.ServerConnection.RetrofitClient
import com.example.stage.mainfragments.mainInterface.OnItemClick
import com.example.stage.mainfragments.maindialog.BasketOptionDialogFragment
import com.example.stage.mainfragments.BasketFragment
import java.lang.Integer.parseInt

class BasketRVAdapter(var context: Context, var basketList: ArrayList<Bundle>, var onItemClick: OnItemClick, var basketFragment: BasketFragment):
    RecyclerView.Adapter<BasketRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.basket_recycler_view_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(basketList[position])
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    //이너 클래스로 홀더 생성후 변수 연결

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        var basketName = itemView?.findViewById<TextView>(R.id.basket_name)
        var basketEnglishName = itemView?.findViewById<TextView>(R.id.basket_english_name)
        var basketOptions = itemView?.findViewById<TextView>(R.id.basket_option_textview)
        var basketOptionChangeButton = itemView?.findViewById<TextView>(R.id.basket_option_change_button)
        var basketMenuNum = itemView?.findViewById<TextView>(R.id.basket_menu_num_textview)
        var basketMenuCost = itemView?.findViewById<TextView>(R.id.basket_menu_cost)
        var basketTotalCost = itemView?.findViewById<TextView>(R.id.basket_total_cost)
        var basketImg = itemView?.findViewById<ImageView>(R.id.basket_image)
        var basketMenuMinusBtn = itemView?.findViewById<ImageButton>(R.id.basket_menu_minus_button)
        var basketMenuPlusBtn = itemView?.findViewById<ImageButton>(R.id.basket_menu_plus_button)
        var basketSelectDeleteBtn = itemView?.findViewById<ImageButton>(R.id.basket_select_delete_button)

        fun changeOptionBtnLan(basketList: Bundle): String {
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            var appLang = pref.getString("app_language","")

            if(appLang.equals("영어") || appLang.equals("English")){
                var cup = ""
                when(basketList.getString("basketCup")) {
                    "매장컵"->{cup = "Store"}
                    "일회용컵" ->{cup = "Disposable"}
                    "개인컵"->{cup = "Personal"}
                }
                return "${basketList.getString("basketHotOrIce")} | " +
                        "${basketList.getString("basketSize")} | " +
                        "${cup}\n" +
                        "Shot${basketList.getString("basketShotNum")} | " +
                        "Syrup${basketList.getString("basketSyrupNum")}"
            }else{
                var hotOrIce = ""
                var size =""
                when(basketList.getString("basketHotOrIce")){
                    "Hot"->{hotOrIce = "뜨거운"}
                    "Ice"->{hotOrIce = "차가운"}
                }
                when(basketList.getString("basketSize")){
                    "Tall"->{size = "작은"}
                    "Venti"->{size = "중간"}
                    "Grande"->{size = "큰"}
                }


                return "$hotOrIce | " +
                        "$size | " +
                        "${basketList.getString("basketCup")}\n" +
                        "샷${basketList.getString("basketShotNum")} | " +
                        "시럽${basketList.getString("basketSyrupNum")}"
            }

        }

        fun bind (basketList: Bundle){
            basketName?.text = basketList.getString("basketName")
            basketEnglishName?.text = basketList.getString("basketEnglishName")
            basketOptions?.text = changeOptionBtnLan(basketList)
            basketMenuNum?.text = basketList.getString("basketMenuNum")
            basketMenuCost?.text = basketList.getString("basketMenuCost")
            basketTotalCost?.text = basketList.getString("basketTotalCost")
            Glide.with(context)
                .load(RetrofitClient.initRetrofit().baseUrl().toString()+basketList.getString("photo"))
                .into(basketImg!!)

            //마이너스 버튼
            basketMenuMinusBtn?.setOnClickListener {
                var basketMenuNumber = parseInt(basketMenuNum?.text.toString())
                if(basketMenuNumber > 1){
                    var newNum = (basketMenuNumber-1).toString()
                    var newTotalCost = ((basketMenuNumber-1) * parseInt(basketMenuCost?.text.toString())).toString()
                    basketMenuNum?.text = newNum
                    basketTotalCost?.text = newTotalCost
                    onItemClick.onClick(adapterPosition,newNum,newTotalCost)
                }
            }

            //플러스 버튼
            basketMenuPlusBtn?.setOnClickListener {
                var basketMenuNumber = parseInt(basketMenuNum?.text.toString())
                if(basketMenuNumber < 20){
                    var newNum = (basketMenuNumber+1).toString()
                    var newTotalCost = ((basketMenuNumber+1) * parseInt(basketMenuCost?.text.toString())).toString()
                    basketMenuNum?.text = newNum
                    basketTotalCost?.text = newTotalCost
                    onItemClick.onClick(adapterPosition,newNum,newTotalCost)
                }
            }

            // 선택삭제 버튼
            basketSelectDeleteBtn?.setOnClickListener {
                onItemClick.onDeleteClick(adapterPosition)
            }
            // 옵션변경사항 전달
            basketOptionChangeButton?.setOnClickListener {

                var bundle = Bundle()
                bundle.putInt("position",adapterPosition)
                bundle.putString("basketHotOrIce",basketList.getString("basketHotOrIce"))
                bundle.putString("basketSize",basketList.getString("basketSize"))
                bundle.putString("basketCup",basketList.getString("basketCup"))
                bundle.putString("basketShotNum",basketList.getString("basketShotNum"))
                bundle.putString("basketSyrupNum",basketList.getString("basketSyrupNum"))
                var optionChangeDialogFragment = BasketOptionDialogFragment(basketFragment)
                optionChangeDialogFragment.arguments = bundle
                onItemClick.onOptionClick(optionChangeDialogFragment)
            }
        }
    }
}