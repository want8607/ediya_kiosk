package com.example.stage

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryFragment : Fragment(){
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.category_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //초기 카테고리 선택
        var categoryRadioBtn1 = view.findViewById<RadioButton>(R.id.category_button1)
        var categoryRadioBtn2 = view.findViewById<RadioButton>(R.id.category_button2)
        categoryRadioBtn1.isChecked = true
        // 리사이클러 뷰 설정
        var categoryDrinkList = arrayListOf<Category>(
            Category("커피","coffee","americano") ,
            Category("베버리지","beverage","menu_beverage_chocolate"),
            Category("차","blending tea", "menu_blending_tea_bokbunja_vin_chaud"),
            Category("플랫치노","flatccino", "menu_flatchino_blueberry_yogurt_flatccino"),
            Category("팥빙수","ice flake", "menu_ice_flake_mango_sherbet_snow_flake"),
            Category("쉐이크&에이드","shake&ade","menu_shake_ade_chocolate_cookie_shake")
        )

        var categoryBakeryList = arrayListOf<Category>(
            Category("빵","bread","menu_bread_chocolate_brownie") ,
            Category("디저트","dessert", "menu_dessert_cream_cheese_muffin"),
            Category("쿠키&기타","cookie&etc","menu_cookie_etc_carbonara_grilled_riceball")

        )
        //카테고리 선택 이벤트

        var categoryAdapter = CategoryRVAdapter(requireActivity(),categoryDrinkList)
        var categoryRecyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerview)
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.setHasFixedSize(true)

        categoryRadioBtn1.setOnCheckedChangeListener { _, b ->
            if(b){
                categoryAdapter.listChanged(categoryBakeryList)
            }else{
                categoryAdapter.listChanged(categoryDrinkList)
            }
        }
        //장바구니 보여주기
        var categoryBasketBtn = view.findViewById<ImageButton>(R.id.category_basket_button)
        categoryBasketBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,BasketFragment()).addToBackStack(null).commit()
        }

        

        

    }
}