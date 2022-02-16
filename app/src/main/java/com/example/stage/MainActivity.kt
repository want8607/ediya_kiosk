package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.stage.mainfragments.BasketFragment
import com.example.stage.mainfragments.MenuFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
    }

    fun sendDataToBasket(bundle: Bundle){
        var basket = BasketFragment()
        basket.arguments = bundle
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
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,fragment).commit()
    }

}
