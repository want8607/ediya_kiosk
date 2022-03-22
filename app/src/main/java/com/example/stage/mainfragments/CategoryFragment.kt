package com.example.stage.mainfragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.ServerConnection.Category
import com.example.stage.ServerConnection.CategoryData
import com.example.stage.ServerConnection.Menu
import com.example.stage.ServerConnection.MenuData
import com.example.stage.StartActivity
import com.example.stage.mainfragments.mainRVAdapter.CategoryRVAdapter
import kotlinx.coroutines.*
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

        var categoryKorName: List<CategoryData> = arrayListOf()
        var categoryEngName: List<CategoryData> = arrayListOf()
        CoroutineScope(Dispatchers.Main).launch{

            //한글 카테고리 가져오기
            val job1 = async(Dispatchers.IO) {
                Log.d("실행1","1")
                mainActivity.requestCategoryApi.getCategory("kr").enqueue(object : Callback<Category> {

                    override fun onResponse(call: Call<Category>, response: Response<Category>) {
                        categoryKorName = response.body()!!.data
                        Log.d("krCategory",categoryKorName.toString())
                    }
                    override fun onFailure(call: Call<Category>, t: Throwable) {
                    }
                })
                Log.d("krCategory",categoryKorName.toString())
                true
            }
            
            //영어 카테고리 가져오기
            val job2 = async(Dispatchers.IO) {
                Log.d("실행2","2")
                mainActivity.requestCategoryApi.getCategory("en").enqueue(object : Callback<Category>{

                    override fun onResponse(call: Call<Category>, response: Response<Category>) {
                        categoryEngName = response.body()!!.data
                    }
                    override fun onFailure(call: Call<Category>, t: Throwable) {
                    }
                })
                Log.d("enCategory",categoryEngName.toString())
            }
            val categoryKr = job1.await()
            val categoryEn = job2.await()
            //메뉴 가져오기
            val job3 = async(Dispatchers.IO) {
                    for (i in categoryKorName.indices) {
                        val menuList: ArrayList<ArrayList<String>> = arrayListOf()
                        mainActivity.requestCategoryApi.getMenu(
                            categoryKorName[i].category_name,
                            "kr"
                        )
                            .enqueue(object : Callback<Menu> {
                                override fun onResponse(
                                    call: Call<Menu>,
                                    response: Response<Menu>
                                ) {
                                    var menuListKr: List<MenuData> =
                                        response.body()!!.data // 한국어 메뉴들

                                    mainActivity.requestCategoryApi.getMenu(
                                        categoryEngName[i].category_name,
                                        "en"
                                    )
                                        .enqueue(object : Callback<Menu> {
                                            override fun onResponse(
                                                call: Call<Menu>,
                                                response: Response<Menu>
                                            ) {
                                                var menuListEn = response.body()!!.data // 영어 메뉴들
                                                for (j in menuListKr.indices) {
                                                    var menu: ArrayList<String> = arrayListOf()
                                                    menu.add(0, menuListKr[j].menu_name)
                                                    menu.add(1, menuListEn[j].menu_name)
                                                    menu.add(2, menuListKr[j].menu_price.toString())
                                                    menu.add(3, menuListKr[j].menu_image)
                                                    menuList.add(menu)
                                                    Log.d("ekwqlel", menuList.toString())
                                                }
                                            }

                                            override fun onFailure(call: Call<Menu>, t: Throwable) {
                                            }
                                        })
                                }

                                override fun onFailure(call: Call<Menu>, t: Throwable) {
                                }
                            })
                        menuLists.add(menuList)
                    }
                Log.d("menuLists",menuLists.toString())
                menuLists
            }

            
            //리사이클러 뷰에 넘길 카테고리 데이터 만들기

            var job4 = async(Dispatchers.Default) {
                for(i in categoryKorName.indices){
                    var category : ArrayList<String> = arrayListOf()
                    category.add(categoryKorName[i].category_name)
                    category.add(categoryEngName[i].category_name)
                    category.add(job3.await()[i][0][3])
                    categoryList.add(category)
                }
                Log.d("Category",categoryList.toString())
                categoryList
            }

            //카테고리 리사이클러
            launch {
            var categoryAdapter = CategoryRVAdapter(mainActivity,job4.await())
            var categoryRecyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerview)
            categoryRecyclerView.adapter = categoryAdapter
            categoryRecyclerView.setHasFixedSize(true)

            Log.d("1",job1.await().toString())
            Log.d("2",job2.await().toString())
            Log.d("3",job3.await().toString())
            Log.d("4",job4.await().toString())
            }
            }
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

        //장바구니 보여주기
        var categoryBasketBtn = view.findViewById<ImageButton>(R.id.category_basket_button)
        categoryBasketBtn.setOnClickListener {
            mainActivity.openBasket()
        }

    }
}

suspend fun