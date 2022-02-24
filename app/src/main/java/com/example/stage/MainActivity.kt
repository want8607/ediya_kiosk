package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.stage.mainfragments.BasketFragment
import com.example.stage.mainfragments.CategoryFragment
import com.example.stage.mainfragments.OrderInfoFragment


class MainActivity : AppCompatActivity() {

    var basketList : ArrayList<Bundle> = arrayListOf()
    var orderStorage : ArrayList<Bundle> = arrayListOf() // 주문번호, 주문시간, 장바구니정보 3
    var orderNumber = 1
    lateinit var basket : BasketFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
        basket = BasketFragment()

        if(savedInstanceState != null){
            basketList = savedInstanceState.getParcelableArrayList("basketList")!!
            orderStorage = savedInstanceState.getParcelableArrayList("orderStorage")!!
            orderNumber = savedInstanceState.getInt("orderNumber")
            basket = supportFragmentManager.getFragment(savedInstanceState,"basketFragment") as BasketFragment
        }
    }

    fun setBasket(bundle: Bundle){
        basketList.add(bundle)
        bundle.putParcelableArrayList("basketList",basketList)
        basket.arguments = bundle
    }

    fun openOrderInfo(){
        var orderInfoFragment = OrderInfoFragment()
        var bundle = Bundle()
        bundle.putParcelableArrayList("orderStorage",orderStorage)
        orderInfoFragment.arguments = bundle
        addFragment(orderInfoFragment)
    }

    fun resetBasket(){
        basketList.clear()
    }

    fun removeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,fragment).addToBackStack(null).commit()
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,fragment).commit()
    }

    fun addOrderInfo(bundle: Bundle){
        orderStorage.add(bundle) //2
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("basketList",basketList)
        outState.putParcelableArrayList("orderStorage",orderStorage)
        outState.putInt("orderNumber",orderNumber)
//        supportFragmentManager.putFragment(outState,"basketFragment",basket)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
    
    override fun onStart() {
        super.onStart()
        Log.d("message","액티비티 실행")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("message","액티비티 재실행")
    }

    override fun onStop() {
        super.onStop()
        Log.d("message","액티비티 멈춤")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("message","액티비티 파괴")
    }
}
