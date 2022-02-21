package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.stage.mainfragments.BasketFragment


class MainActivity : AppCompatActivity() {
    var basketList : ArrayList<Bundle> = arrayListOf()
    lateinit var basket : BasketFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
        basket = BasketFragment()
    }

    fun setBasket(bundle: Bundle){
        basketList.add(bundle)
        bundle.putParcelableArrayList("basketlist",basketList)
        basket.arguments = bundle
        Log.d("setBasket","실행됨")
    }

    fun setDataAtFragment(fragment: Fragment, bundle: Bundle){
        fragment.arguments = bundle
        addFragment(fragment)
    }

    fun removeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,fragment).addToBackStack(null).commit()
        Log.d("addfragment","실행됨")
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,fragment).commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d("message","액티비티 실행")
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
