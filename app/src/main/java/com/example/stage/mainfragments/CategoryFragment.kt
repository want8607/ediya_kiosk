package com.example.stage.mainfragments

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.StartActivity

class CategoryFragment : Fragment(){
    lateinit var mainActivity: MainActivity
    lateinit var categoryBakeryList : ArrayList<Category>
    lateinit var categoryDrinkList : ArrayList<Category>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("message_category","onAttach")
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mainActivity = activity as MainActivity
        var view: View = inflater.inflate(R.layout.category_fragment,container,false)
        categoryDrinkList= arrayListOf(
            Category("커피","coffee","americano") ,
            Category("베버리지","beverage","menu_beverage_chocolate"),
            Category("차","blending tea", "menu_blending_tea_bokbunja_vin_chaud"),
            Category("플랫치노","flatccino", "menu_flatchino_blueberry_yogurt_flatccino"),
            Category("팥빙수","ice flake", "menu_ice_flake_mango_sherbet_snow_flake"),
            Category("쉐이크&에이드","shake&ade","menu_shake_ade_chocolate_cookie_shake")
        )

        categoryBakeryList = arrayListOf(
            Category("빵","bread","menu_bread_chocolate_brownie") ,
            Category("디저트","dessert", "menu_dessert_cream_cheese_muffin"),
            Category("쿠키&기타","cookie&etc","menu_cookie_etc_carbonara_grilled_riceball")
        )

        Log.d("message_category","onCreateView")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //팝업메뉴 설정
        var wrapper = ContextThemeWrapper(mainActivity,R.style.PopupMenu)
        var myInfoBtn = view.findViewById<ImageButton>(R.id.category_order_info_button)
        var popupMenu = PopupMenu(wrapper, myInfoBtn)
        mainActivity.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.order_info -> {mainActivity.openOrderInfo()}
                R.id.setting ->{mainActivity.addFragment(SettingFragment(),"setting")}
                R.id.logOut -> {
                    var intent = Intent(activity, StartActivity::class.java)
                    startActivity(intent)
                    mainActivity.finish()}

            }
            false
        })
        //주문정보 보기
        myInfoBtn.setOnClickListener {
                popupMenu.show()
        }

        var innerCategoryDrinkList : ArrayList<Category> = arrayListOf()
        for ( i in categoryDrinkList.indices){
            innerCategoryDrinkList.add(categoryDrinkList[i])
        }
        var innerCategoryBakeryList : ArrayList<Category> = arrayListOf()
        for ( i in categoryBakeryList.indices){
            innerCategoryBakeryList.add(categoryBakeryList[i])
        }
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
            mainActivity.openBasket()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}