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
    lateinit var coffeeMenuList : ArrayList<ArrayList<String>>
    lateinit var beverageMenuList : ArrayList<ArrayList<String>>
    lateinit var blendingTeaMenuList : ArrayList<ArrayList<String>>
    lateinit var flatchinoMenuList : ArrayList<ArrayList<String>>
    lateinit var iceFlakeMenuList : ArrayList<ArrayList<String>>
    lateinit var shakeAndAdeMenuList : ArrayList<ArrayList<String>>
    lateinit var breadMenuList : ArrayList<ArrayList<String>>
    lateinit var cookieAndEtcMenuList : ArrayList<ArrayList<String>>
    lateinit var dessertMenuList : ArrayList<ArrayList<String>>


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.menu_fragment,container,false)
        mainActivity = activity as MainActivity

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedMenu = arguments?.getInt("title")
        var isDrink = arguments?.getBoolean("isChecked")
        //카테고리 이름 설정
        var categoryName = view.findViewById<TextView>(R.id.menu_category_name)
        categoryName.text = arguments?.getString("categoryName")


        //넘겨받은 값으로 선택한 카테고리 인식
        var showList =
            when(setOf(selectedMenu,isDrink)){
                setOf(0,true) -> coffeeMenuList
                setOf(1,true) -> beverageMenuList
                setOf(2,true) -> blendingTeaMenuList
                setOf(3,true) -> flatchinoMenuList
                setOf(4,true) -> iceFlakeMenuList
                setOf(5,true) -> shakeAndAdeMenuList
                setOf(0,false) -> breadMenuList
                setOf(1,false) -> dessertMenuList
                setOf(2,false) -> cookieAndEtcMenuList
                else -> coffeeMenuList
            }

        //메뉴 리사이클러뷰 설정
        var menuAdapter = MenuRVAdapter(mainActivity,showList)

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