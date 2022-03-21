package com.example.stage.mainfragments

import android.content.Context
import android.content.Intent
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
import com.example.stage.ServerConnection.Category
import com.example.stage.ServerConnection.CategoryData
import com.example.stage.ServerConnection.Menu
import com.example.stage.StartActivity
import com.example.stage.mainfragments.mainRVAdapter.CategoryRVAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CategoryFragment : Fragment(){
    lateinit var mainActivity: MainActivity
    lateinit var categoryList : ArrayList<ArrayList<String>>
    lateinit var menuList : ArrayList<ArrayList<String>>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view: View = inflater.inflate(R.layout.category_fragment,container,false)

        mainActivity.requestCategoryApi.getCategory("kr").enqueue(object : Callback<Category> {

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                categoryList = arrayListOf()
                var categoryEngName = listOf<CategoryData>()
                var categoryKorName = listOf<CategoryData>()
                
                //한글 카테고리 이름 가져오기
                categoryKorName = response.body()!!.data
                
                //영어 카테고리 이름 가져오기
                mainActivity.requestCategoryApi.getCategory("en").enqueue(object : Callback<Category>{
                    override fun onResponse(call: Call<Category>, response: Response<Category>) {
                        categoryEngName = response.body()!!.data
                        Log.d("categoryEng",categoryEngName.toString())
                        //메뉴 가져오기
                        for(i in categoryEngName.indices) {
                            mainActivity.requestCategoryApi.getMenu(categoryKorName[i].category_name, "kr")
                                .enqueue(object : Callback<Menu> {
                                    override fun onResponse(call: Call<Menu>, response: Response<Menu>) {
                                        Log.d("menu",response.body()!!.data.toString())
                                    }

                                    override fun onFailure(call: Call<Menu>, t: Throwable) {
                                    }
                                })

                            mainActivity.requestCategoryApi.getMenu(categoryEngName[i].category_name, "en")
                                .enqueue(object : Callback<Menu> {
                                    override fun onResponse(call: Call<Menu>, response: Response<Menu>) {
                                        Log.d("menu",response.body()!!.data.toString())
                                    }

                                    override fun onFailure(call: Call<Menu>, t: Throwable) {
                                    }
                                })
                        }
                        //데이터 파싱
                        for(i in categoryKorName.indices){
                            var getList = arrayListOf<String>()
                            getList.add(categoryKorName[i].category_name)
                            getList.add(categoryEngName[i].category_name)
                            categoryList.add(getList)
                        }
                        Log.d("category",categoryList.toString())

                    }

                    override fun onFailure(call: Call<Category>, t: Throwable) {
                    }
                }
                )
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
            }

        })

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //팝업메뉴 설정
//        var wrapper = ContextThemeWrapper(mainActivity,R.style.PopupMenu)
//        var myInfoBtn = view.findViewById<ImageButton>(R.id.category_order_info_button)
//        var popupMenu = PopupMenu(wrapper, myInfoBtn)
//        mainActivity.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
//        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.order_info -> {mainActivity.addFragment(OrderInfoFragment(),"orderInfo")}
//                R.id.setting ->{mainActivity.addFragment(SettingFragment(),"setting")}
//                R.id.logOut -> {
//                    mainActivity.loginFlag = "true"
//                    var intent = Intent(activity, StartActivity::class.java)
//                    startActivity(intent)
//                    mainActivity.finish()}
//
//            }
//            false
//        })
//        //주문정보 보기
//        myInfoBtn.setOnClickListener {
//                popupMenu.show()
//        }
//
//        var innerCategoryDrinkList : ArrayList<ArrayList<String>> = arrayListOf()
//        for ( i in categoryDrinkList.indices){
//            innerCategoryDrinkList.add(categoryDrinkList[i])
//        }
//        var innerCategoryBakeryList : ArrayList<ArrayList<String>> = arrayListOf()
//        for ( i in categoryBakeryList.indices){
//            innerCategoryBakeryList.add(categoryBakeryList[i])
//        }
//        //카테고리 선택 이벤트
//        var categoryAdapter = CategoryRVAdapter(mainActivity,categoryDrinkList)
//        var categoryRecyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerview)
//        categoryRecyclerView.adapter = categoryAdapter
//        categoryRecyclerView.setHasFixedSize(true)
//
//        //카테고리 선택
//        var categoryRadioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
//        var categoryRadioBtn1 = view.findViewById<RadioButton>(R.id.category_button1)
//        categoryRadioBtn1.isChecked = true
//        categoryRadioGroup.setOnCheckedChangeListener { _, checkedId ->
//
//            when (checkedId) {
//                R.id.category_button1 -> {
//                    categoryAdapter.isChecked = true
//                    categoryAdapter.listChanged(innerCategoryDrinkList)
//                }
//                R.id.category_button2 -> {
//                    categoryAdapter.isChecked = false
//                    categoryAdapter.listChanged(innerCategoryBakeryList)
//                }
//            }
//        }
//        //장바구니 보여주기
//        var categoryBasketBtn = view.findViewById<ImageButton>(R.id.category_basket_button)
//        categoryBasketBtn.setOnClickListener {
//            mainActivity.openBasket()
//        }
//
//    }
}