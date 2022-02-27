package com.example.stage.mainfragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.mainInterface.OnItemClick
import kotlin.properties.Delegates

class BasketFragment: Fragment(), OnItemClick {
    lateinit var mainActivity : MainActivity
    lateinit var basketRecyclerView: RecyclerView
    lateinit var basketRVAdapter: BasketRVAdapter
    lateinit var menuNumView: TextView
    lateinit var totalCostView: TextView
    var basketList: ArrayList<Bundle> = arrayListOf()
    var totalCost by Delegates.notNull<Int>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null){
            basketList = arguments?.getParcelableArrayList("basketList")!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        totalCost = 0
        mainActivity = activity as MainActivity
        var view: View = inflater.inflate(R.layout.basket_fragment,container,false)
        //리사이클 뷰 생성
        basketRVAdapter = BasketRVAdapter(mainActivity, basketList,this,this)
        basketRecyclerView = view.findViewById<RecyclerView>(R.id.basket_recyclerview)
        basketRecyclerView.adapter = basketRVAdapter
        basketRecyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //초기 UI 설정
        menuNumView = view.findViewById(R.id.basket_menu_total_num_textview)
        totalCostView = view.findViewById(R.id.basket_total_cost_textview)
        updateTotalMenuNum(menuNumView,totalCostView)

        //뒤로가기
        var basketBackBtn = view.findViewById<ImageButton>(R.id.basket_back_button)
        basketBackBtn.setOnClickListener {
            mainActivity.removeFragment(this)
        }

        //전체삭제
        var basketAllDelete = view.findViewById<Button>(R.id.basket_delete_all_button)
        basketAllDelete.setOnClickListener {
            basketList.clear()
            basketRVAdapter.notifyDataSetChanged()
            updateTotalMenuNum(menuNumView,totalCostView)
        }

        //결제버튼 눌렀을 때
        var basketPayBtn = view.findViewById<Button>(R.id.basket_payment_button)
        basketPayBtn.setOnClickListener {
            if(basketList.isNotEmpty()) {
                var paymentFragment = PaymentFragment()
                var bundle = Bundle()
                var orderList :ArrayList<Bundle> = arrayListOf()
                for (i in basketList.indices){
                    orderList.add(basketList[i])
                }
                bundle.putParcelableArrayList("basketList", orderList)
                bundle.putInt("totalCost", totalCost)
                paymentFragment.arguments = bundle
                mainActivity.addFragment(paymentFragment)
            }else{
                var builder = AlertDialog.Builder(activity)
                builder.setCancelable(false)
                builder.setMessage("메뉴를 담아주세요.")
                    .setPositiveButton("확인", DialogInterface.OnClickListener{
                            dialogInterface, i->
                    })
                builder.show()
            }
        }
    }
    //옵션 변경
    fun changeOption(bundle: Bundle){
        var position = bundle.getInt("position")
        var basketHotOrIce = bundle.getString("basketHotOrIce")
        var basketSize = bundle.getString("basketSize")
        var basketCup = bundle.getString("basketCup")
        var basketShotNum = bundle.getString("basketShotNum")
        var basketSyrupNum = bundle.getString("basketSyrupNum")
        var optionChangCost = bundle.getString("optionChangCost")?.toInt()
        var newMenuCost = (basketList[position].getString("basketMenuCost")?.toInt()?.plus(optionChangCost!!)).toString()
        var newTotalCost = (basketList[position].getString( "basketMenuNum")?.toInt()?.times(newMenuCost.toInt())).toString()
        basketList[position].putString(
            "basketHotOrIce",basketHotOrIce
        )
        basketList[position].putString(
            "basketSize",basketSize
        )
        basketList[position].putString(
            "basketCup",basketCup
        )
        basketList[position].putString(
            "basketShotNum",basketShotNum
        )
        basketList[position].putString(
            "basketSyrupNum",basketSyrupNum
        )
        basketList[position].putString(
            "basketMenuCost",newMenuCost
        )
        basketList[position].putString(
            "basketTotalCost",newTotalCost
        )
        basketRVAdapter.notifyItemChanged(position)
        updateTotalMenuNum(menuNumView,totalCostView)
    }

    //총 개수및 가격 업데이트
    fun updateTotalMenuNum(menuNumView: TextView,totalCostView: TextView){
        var totalMenuNum = 0
        totalCost = 0
        for (i in basketList.indices) {
            totalMenuNum += basketList[i].getString("basketMenuNum")?.toInt()!!
        }
        for (j in basketList.indices) {
            totalCost += basketList[j].getString("basketTotalCost")?.toInt()!!
        }
        menuNumView.text = totalMenuNum.toString()
        totalCostView.text = totalCost.toString()

    }
    //메뉴 개수 변경 이벤트
    override fun onClick(position: Int, value: String, totalCost: String) {
        basketList[position].putString(
            "basketMenuNum",value)
        basketList[position].putString(
            "basketTotalCost",totalCost)
        updateTotalMenuNum(menuNumView,totalCostView)
    }
    // 옵션변경버튼 클릭
    override fun onOptionClick(fragment: DialogFragment) {
        fragment.show(mainActivity.supportFragmentManager,"option")
    }
    //아이템 삭제버튼클릭
    override fun onDeleteClick(position: Int) {
        basketList.removeAt(position)
        for (i in basketList.indices){

            basketList[i].getString("basketName")?.let { Log.d("basket", it) }
        }
        basketRVAdapter.notifyItemRemoved(position)
        updateTotalMenuNum(menuNumView,totalCostView)
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