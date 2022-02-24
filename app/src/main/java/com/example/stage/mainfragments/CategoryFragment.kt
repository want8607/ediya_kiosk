package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R

class CategoryFragment : Fragment(){
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.category_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var orderInfoBtn = view.findViewById<ImageButton>(R.id.category_order_info_button)

        //주문정보 보기
        orderInfoBtn.setOnClickListener {
            mainActivity.openOrderInfo()
        }

        // 리사이클러 뷰 설정
        var categoryDrinkList : MutableList<Category> = mutableListOf(
            Category("커피","coffee","americano") ,
            Category("베버리지","beverage","menu_beverage_chocolate"),
            Category("차","blending tea", "menu_blending_tea_bokbunja_vin_chaud"),
            Category("플랫치노","flatccino", "menu_flatchino_blueberry_yogurt_flatccino"),
            Category("팥빙수","ice flake", "menu_ice_flake_mango_sherbet_snow_flake"),
            Category("쉐이크&에이드","shake&ade","menu_shake_ade_chocolate_cookie_shake")
        )

        var categoryBakeryList : MutableList<Category> = mutableListOf(
            Category("빵","bread","menu_bread_chocolate_brownie") ,
            Category("디저트","dessert", "menu_dessert_cream_cheese_muffin"),
            Category("쿠키&기타","cookie&etc","menu_cookie_etc_carbonara_grilled_riceball")

        )
        var innerCategoryDrinkList = categoryDrinkList.toMutableList()
        var innerCategoryBakeryList = categoryBakeryList.toMutableList()

        //카테고리 선택 이벤트
        var categoryAdapter = CategoryRVAdapter(mainActivity,categoryDrinkList)
        var categoryRecyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerview)
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.setHasFixedSize(true)

        //카테고리 선택
        var categoryRadioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        var categoryRadioBtn1 = view.findViewById<RadioButton>(R.id.category_button1)
        categoryRadioBtn1.isChecked = true
        categoryRadioGroup.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.category_button1 -> {
                    categoryAdapter.isChecked = true
                    categoryAdapter.listChanged(innerCategoryDrinkList)
                }
                R.id.category_button2 -> {
                    categoryAdapter.isChecked = false
                    categoryAdapter.listChanged(innerCategoryBakeryList)
                }
            }
        }
        //장바구니 보여주기
        var categoryBasketBtn = view.findViewById<ImageButton>(R.id.category_basket_button)
        categoryBasketBtn.setOnClickListener {
            mainActivity.addFragment(mainActivity.basket)
        }

    }
}