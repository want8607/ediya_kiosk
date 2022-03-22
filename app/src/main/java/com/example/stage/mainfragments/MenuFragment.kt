package com.example.stage.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.mainfragments.mainRVAdapter.MenuRVAdapter

class MenuFragment : Fragment(){
    lateinit var mainActivity : MainActivity



    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.menu_fragment,container,false)
        mainActivity = activity as MainActivity

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedMenu = arguments?.getInt("title")

        //카테고리 이름 설정
        var categoryName = view.findViewById<TextView>(R.id.menu_category_name)
        categoryName.text = arguments?.getString("categoryName")

        //메뉴 리사이클러뷰 설정
        var menuAdapter = MenuRVAdapter(mainActivity,mainActivity.menuLists[selectedMenu!!])

        var menuRecyclerView = view.findViewById<RecyclerView>(R.id.menu_recyclerview)
        menuRecyclerView.adapter = menuAdapter
        menuRecyclerView.setHasFixedSize(true)


        //뒤로가기 버튼
        var menuBackBtn = view.findViewById<ImageButton>(R.id.menu_back_button)
        menuBackBtn.setOnClickListener {
            mainActivity.removeFragment(this,"menu")
        }

        //장바구니 버튼
        var menuBasketBtn = view.findViewById<ImageButton>(R.id.menu_basket_button)
        menuBasketBtn.setOnClickListener {
            mainActivity.openBasket()
        }
    }



}