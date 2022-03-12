package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PaymentFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var basketList: ArrayList<Bundle>
    lateinit var paymentRecyclerView: RecyclerView
    lateinit var paymentRVAdapter: PaymentRVAdapter
    var totalCost = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = activity as MainActivity
        basketList = arguments?.getParcelableArrayList("basketList")!!
        totalCost = arguments?.getInt("totalCost")!!

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.payment_fragment,container,false)

        paymentRVAdapter = PaymentRVAdapter(mainActivity,basketList)
        paymentRecyclerView = view.findViewById<RecyclerView>(R.id.payment_recyclerview)
        paymentRecyclerView.adapter = paymentRVAdapter
        paymentRecyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var paymentBackBtn = view.findViewById<ImageButton>(R.id.payment_back_button)
        paymentBackBtn.setOnClickListener {
            mainActivity.removeFragment(this,"payment")
        }

        //결제하기
        var paymentPayBtn = view.findViewById<Button>(R.id.payment_pay_button)
        paymentPayBtn.text = (totalCost.toString()+"￦ "+getString(R.string.pay))
        paymentPayBtn.setOnClickListener {
            //주문 내역 orders에 저장
            val dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")
            val currentDate = LocalDateTime.now()
            var value = arrayListOf(
                arrayListOf("id",mainActivity.userId,"TEXT"),
                arrayListOf("orderTime",currentDate.format(dateFormat),"TEXT")
            )
            mainActivity.databaseControl.createData(mainActivity.writableDb,"orders",value)

            //orders에서 seq가져오기
            var seq =mainActivity.databaseControl.readData(mainActivity.readableDb,"orders",
                arrayListOf(
                    arrayListOf("id",mainActivity.userId),
                    arrayListOf("orderTime",currentDate.format(dateFormat))
                )
            )[0][0]

            //orderMenus에 추가
            for( i in basketList.indices){
                value = arrayListOf(
                    arrayListOf("seq",seq,"INTEGER"),
                    arrayListOf("menuName",basketList[i].getString("basketName")!!,"TEXT"),
                    arrayListOf("menuCost",basketList[i].getString("basketMenuCost")!!,"INTEGER"),
                    arrayListOf("menuNum",basketList[i].getString("basketMenuNum")!!,"INTEGER")
                )
                mainActivity.databaseControl.createData(mainActivity.writableDb,"orderMenus",value)
            }

            //영수증 띄우기
            var recipeDialog = RecipeDialogFragment()
            var bundle = Bundle()
            bundle.putInt("seq",seq.toInt())
            recipeDialog.arguments = bundle
            recipeDialog.show(mainActivity.supportFragmentManager,"recipeDialog")
            mainActivity.basketService.resetBasket()
            mainActivity.basketService.updateTotalMenuNum()
        }
    }
}
