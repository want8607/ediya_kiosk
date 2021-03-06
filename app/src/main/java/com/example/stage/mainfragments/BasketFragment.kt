package com.example.stage.mainfragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.mainfragments.mainInterface.OnItemClick
import com.example.stage.mainfragments.mainRVAdapter.BasketRVAdapter
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
        val view: View = inflater.inflate(R.layout.basket_fragment,container,false)
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
        updateTotalUi(menuNumView,totalCostView)

        //뒤로가기
        val basketBackBtn = view.findViewById<ImageButton>(R.id.basket_back_button)
        basketBackBtn.setOnClickListener {
            mainActivity.removeFragment(this,"basket")
        }

        //전체삭제
        val basketAllDelete = view.findViewById<Button>(R.id.basket_delete_all_button)
        basketAllDelete.setOnClickListener {
            basketList.clear()
            basketRVAdapter.notifyDataSetChanged()
            updateTotalUi(menuNumView,totalCostView)
        }

        //결제버튼 눌렀을 때
        val basketPayBtn = view.findViewById<Button>(R.id.basket_payment_button)
        basketPayBtn.setOnClickListener {
            if(basketList.isNotEmpty()) {
                val paymentFragment = PaymentFragment()
                val bundle = Bundle()
                val orderList :ArrayList<Bundle> = arrayListOf()
                for (i in basketList.indices){
                    orderList.add(basketList[i])
                }
                bundle.putParcelableArrayList("basketList", orderList)
                bundle.putInt("totalCost", mainActivity.basketService.totalCost)
                paymentFragment.arguments = bundle
                mainActivity.addFragment(paymentFragment,"payment")
            }else{
                var text = getString(R.string.toast_add_menu)
                Toast.makeText(mainActivity, text, Toast.LENGTH_SHORT).show()
            }
        }
    }
    //옵션 변경
    fun changeOption(bundle: Bundle){
        val position = bundle.getInt("position")
        val basketHotOrIce = bundle.getString("basketHotOrIce")
        val basketSize = bundle.getString("basketSize")
        val basketCup = bundle.getString("basketCup")
        val basketShotNum = bundle.getString("basketShotNum")
        val basketSyrupNum = bundle.getString("basketSyrupNum")
        val optionChangCost = bundle.getString("optionChangCost")?.toInt()
        val newMenuCost = (basketList[position].getString("basketMenuCost")?.toInt()?.plus(optionChangCost!!)).toString()
        val newTotalCost = (basketList[position].getString( "basketMenuNum")?.toInt()?.times(newMenuCost.toInt())).toString()
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
        updateTotalUi(menuNumView,totalCostView)
    }

    //총 개수및 가격 업데이트
    fun updateTotalUi(menuNumView: TextView,totalCostView: TextView){
        mainActivity.basketService.updateTotalMenuNum()
        menuNumView.text = mainActivity.basketService.totalMenuNum.toString()
        totalCostView.text = mainActivity.basketService.totalCost.toString()

    }
    //메뉴 개수 변경 이벤트
    override fun onClick(position: Int, value: String, totalCost: String) {
        basketList[position].putString(
            "basketMenuNum",value)
        basketList[position].putString(
            "basketTotalCost",totalCost)
        updateTotalUi(menuNumView,totalCostView)
    }
    // 옵션변경버튼 클릭
    override fun onOptionClick(fragment: DialogFragment) {
        fragment.show(mainActivity.supportFragmentManager,"option")
    }
    //아이템 삭제버튼클릭
    override fun onDeleteClick(position: Int) {
        basketList.removeAt(position)
        basketRVAdapter.notifyItemRemoved(position)
        updateTotalUi(menuNumView,totalCostView)
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