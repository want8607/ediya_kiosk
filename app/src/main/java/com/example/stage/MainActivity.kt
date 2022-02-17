package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.stage.mainfragments.BasketFragment
import com.example.stage.mainfragments.MenuFragment

class MainActivity : AppCompatActivity() {
    var basketList : ArrayList<Bundle> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)
    }

    fun addBasketList(bundle: Bundle){
        basketList.add(bundle)
    }

    fun openBasket(){
        var basket =BasketFragment()
        var bundle = Bundle()
        bundle.putParcelableArrayList("basketlist",basketList)
        basket.arguments = bundle
        addFragment(basket)
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
