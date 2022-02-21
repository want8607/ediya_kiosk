package com.example.stage.mainfragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.mainInterface.OnItemClick

class BasketFragment: Fragment(), OnItemClick {
    lateinit var mainActivity : MainActivity
    var basketList: ArrayList<Bundle> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.basket_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity

        if (arguments != null){
            basketList = arguments?.getParcelableArrayList("basketlist")!!
        }

        //리사이클 뷰 생성
        var basketRVAdapter = BasketRVAdapter(mainActivity, basketList,this)
        var basketRecyclerView = view.findViewById<RecyclerView>(R.id.basket_recyclerview)
        basketRecyclerView.adapter = basketRVAdapter
        basketRecyclerView.setHasFixedSize(true)

        //뒤로가기
        var basketBackBtn = view.findViewById<ImageButton>(R.id.basket_back_button)
        basketBackBtn.setOnClickListener {
            mainActivity.removeFragment(this)
        }

        //결제버튼 눌렀을 때
        var basketPayBtn = view.findViewById<Button>(R.id.basket_payment_button)
        basketPayBtn.setOnClickListener {
            mainActivity.addFragment(PaymentFragment())
        }
    }
    
    //아이템 클릭 이벤트
    override fun onClick(position: Int, value: String, totalCost: String) {
        basketList[position].putString(
            "basketMenuNum",value)
        basketList[position].putString(
            "basketTotalCost",totalCost)
    }

    override fun onOptionClick(fragment: DialogFragment) {
        fragment.show(mainActivity.supportFragmentManager,"option")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onStart() {
        super.onStart()
        Log.d("message","시작")
    }

    override fun onStop() {
        super.onStop()
        Log.d("message","멈춤")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("message","파괴됨")
    }



}