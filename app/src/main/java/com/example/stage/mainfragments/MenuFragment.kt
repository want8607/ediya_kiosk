package com.example.stage.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R

class MenuFragment : Fragment(){
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.menu_fragment,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mainActivity = activity as MainActivity
        var selectedMenu = arguments?.getInt("title")
        var isChecked = arguments?.getBoolean("isChecked")

        var coffee : MutableList<Menu> = mutableListOf(
            Menu("아메리카노","Americano","3200","americano"),
            Menu("아포가토","Affogato","4500","menu_coffe_affogato_origianl"),
            Menu("카페라떼","Caffe Latte","3700","menu_coffe_cafe_latte"),
            Menu("카페모카","Caffe Moca","3900","menu_coffe_cafe_moca"),
            Menu("카푸치노","Americano","3200","menu_coffe_cappuchino"),
            Menu("아메리카노","Americano","3200","menu_coffe_caramel_macchiato"),
            Menu("아메리카노","Americano","3200","menu_coffe_espresso"),
            Menu("아메리카노","Americano","3200","menu_coffe_vanilla_latte")

        )

        var


        //메뉴 리사이클러뷰 설정
        var menuAdapter = MenuRVAdapter(mainActivity,categoryDrinkList){ item ->
            mainActivity.addFragment(SelectMenuFragment())
        }

        var menuRecyclerView = view.findViewById<RecyclerView>(R.id.menu_recyclerview)
        menuRecyclerView.adapter = menuAdapter
        menuRecyclerView.setHasFixedSize(true)


        //뒤로가기 버튼
        var menuBackBtn = view.findViewById<ImageButton>(R.id.menu_back_button)
        menuBackBtn.setOnClickListener {
            mainActivity.removeFragment(this)
        }

        //장바구니 버튼
        var menuBasketBtn = view.findViewById<ImageButton>(R.id.menu_basket_button)
        menuBasketBtn.setOnClickListener {
            mainActivity.addFragment(BasketFragment())
        }
    }



}