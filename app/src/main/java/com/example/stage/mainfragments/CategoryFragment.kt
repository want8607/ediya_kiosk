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
import com.example.stage.ServerConnection.CategoryData
import com.example.stage.ServerConnection.MenuData
import com.example.stage.StartActivity
import com.example.stage.mainfragments.mainRVAdapter.CategoryRVAdapter
import kotlinx.coroutines.*

class CategoryFragment : Fragment(){
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view: View = inflater.inflate(R.layout.category_fragment,container,false)

        lifecycleScope.launch {
            Log.d("aaa","11111")
            //카테고리 값 가져오기
            val categoryKorName: List<CategoryData> = async(Dispatchers.IO) {
                mainActivity.requestCategoryApi.getCategorySuspend("kr").data
            }.await()
            Log.d("aaa","1")
            val categoryEngName : List<CategoryData> = async(Dispatchers.IO) {
                mainActivity.requestCategoryApi.getCategorySuspend("en").data
            }.await()

            //카테고리 정보로 메뉴만들기
            for (i in categoryKorName.indices) {
                val menuListKr: List<MenuData> = async(Dispatchers.IO) {
                    mainActivity.requestCategoryApi.getMenuSuspend(categoryKorName[i].category_name, "kr").data
                }.await()

                val menuListEng: List<MenuData> = async(Dispatchers.IO) {
                    mainActivity.requestCategoryApi.getMenuSuspend(categoryEngName[i].category_name, "en").data
                }.await()
                val menuList: ArrayList<ArrayList<String>> = arrayListOf()
                for (j in menuListKr.indices) {
                    var menu: ArrayList<String> = arrayListOf()
                    menu.add(0, menuListKr[j].menu_name)
                    menu.add(1, menuListEng[j].menu_name)
                    menu.add(2, menuListKr[j].menu_price.toString())
                    menu.add(3, menuListKr[j].menu_image)
                    menuList.add(menu)
                }
                mainActivity.menuLists.add(menuList)
            }
            //리사이클러에 넣을 카테고리 만들기
            val categoryList : ArrayList<ArrayList<String>> = arrayListOf()
            for(i in categoryKorName.indices){
                var category : ArrayList<String> = arrayListOf()
                category.add(categoryKorName[i].category_name)
                category.add(categoryKorName[i].category_name)
                category.add(mainActivity.menuLists[i][0][3])
                categoryList.add(category)
            }
            //카테고리 리사이클러
            val categoryAdapter = CategoryRVAdapter(mainActivity,categoryList)
            val categoryRecyclerView = view.findViewById<RecyclerView>(R.id.category_recyclerview)
            categoryRecyclerView.adapter = categoryAdapter
            categoryRecyclerView.setHasFixedSize(true)
            Log.d("aaa","3")
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
        Log.d("ggg","12")
    }
}


