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
    lateinit var menuLists : ArrayList<ArrayList<ArrayList<String>>>
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view: View = inflater.inflate(R.layout.category_fragment,container,false)
        categoryList = arrayListOf()
        menuLists = arrayListOf()
        mainActivity.requestCategoryApi.getCategory("kr").enqueue(object : Callback<Category> {

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                var categoryEngName: List<CategoryData>

                //한글 카테고리 이름 가져오기
                var categoryKorName: List<CategoryData> = response.body()!!.data
                
                //영어 카테고리 이름 가져오기
                mainActivity.requestCategoryApi.getCategory("en").enqueue(object : Callback<Category>{

                    override fun onResponse(call: Call<Category>, response: Response<Category>) {
                        categoryEngName = response.body()!!.data

                        //메뉴 데이터 가져오기
                        for(i in categoryEngName.indices) {

                            var category = arrayListOf<String>()
                            category.add(categoryKorName[i].category_name)
                            category.add(categoryEngName[i].category_name)

                            mainActivity.requestCategoryApi.getMenu(categoryKorName[i].category_name, "kr")
                                .enqueue(object : Callback<Menu> {
                                    override fun onResponse(call: Call<Menu>, response: Response<Menu>) {
                                        var menuList = arrayListOf<ArrayList<String>>()
                                        var menuKr=response.body()!!.data // 한국어 메뉴들
                                        category.add(menuKr[0].menu_image)

                                        //영어메뉴이름 가져오기
                                        mainActivity.requestCategoryApi.getMenu(categoryEngName[i].category_name, "en")
                                            .enqueue(object : Callback<Menu> {

                                                override fun onResponse(call: Call<Menu>, response: Response<Menu>) {

                                                    var menuEn = response.body()!!.data // 영어 메뉴들

                                                    for(j in menuKr.indices){
                                                        var menu = arrayListOf<String>()
                                                        menu.add(menuKr[j].menu_name)
                                                        menu.add(menuEn[j].menu_name)
                                                        menu.add(menuKr[j].menu_price.toString())
                                                        menu.add(menuKr[j].menu_image)
                                                        menuList.add(menu)
                                                    }

                                                }
                                                override fun onFailure(call: Call<Menu>, t: Throwable) {
                                                }
                                            })
                                        menuLists.add(menuList) //3중 리스트 완성
                                    }
                                    override fun onFailure(call: Call<Menu>, t: Throwable) {
                                    }
                                })
                            categoryList.add(category)
                        }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //팝업메뉴 설정
        var wrapper = ContextThemeWrapper(mainActivity,R.style.PopupMenu)
        var myInfoBtn = view.findViewById<ImageButton>(R.id.category_order_info_button)
        var popupMenu = PopupMenu(wrapper, myInfoBtn)
        mainActivity.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.order_info -> {mainActivity.addFragment(OrderInfoFragment(),"orderInfo")}
                R.id.setting ->{mainActivity.addFragment(SettingFragment(),"setting")}
                R.id.logOut -> {
                    mainActivity.loginFlag = "true"
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

        //카테고리 선택 이벤트
        var categoryAdapter = CategoryRVAdapter(mainActivity,categoryList)
        var categoryRecyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerview)
        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.setHasFixedSize(true)

        //장바구니 보여주기
        var categoryBasketBtn = view.findViewById<ImageButton>(R.id.category_basket_button)
        categoryBasketBtn.setOnClickListener {
            mainActivity.openBasket()
        }

    }
}