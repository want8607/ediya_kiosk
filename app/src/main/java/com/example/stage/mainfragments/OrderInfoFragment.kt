package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.mainInterface.OrderInfoItemClick

class OrderInfoFragment : Fragment(), OrderInfoItemClick{
    lateinit var mainActivity: MainActivity
    lateinit var orderList: ArrayList<Bundle>
    lateinit var orderInfoRecyclerView: RecyclerView
    lateinit var orderInfoRVAdapter: OrderInfoRVAdapter
    lateinit var totalCost: ArrayList<Int>
    lateinit var paymentTime: ArrayList<String>
    lateinit var orderInfo: ArrayList<Bundle>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
        orderList = arguments?.getParcelableArrayList("orderList")!!
        totalCost = arguments?.getIntegerArrayList("totalCost")!!
        paymentTime = arguments?.getStringArrayList("paymentTime")!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.order_info_fragment,container,false)
        orderInfoRVAdapter = OrderInfoRVAdapter(mainActivity,orderList,this)
        orderInfoRecyclerView = view.findViewById<RecyclerView>(R.id.payment_recyclerview)
        orderInfoRecyclerView.adapter = orderInfoRVAdapter
        orderInfoRecyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var orderInfoBackBtn = view.findViewById<ImageButton>(R.id.order_info_back_button)
        orderInfoBackBtn.setOnClickListener {
            mainActivity.removeFragment(this)
        }
    }

    override fun onclick(fragment: RecipeDialogFragment,position:Int) {
        var recipeDialog = fragment
        var bundle = Bundle()
        bundle.putParcelableArrayList("basketList",orderList[position])
        bundle.putInt("totalCost",totalCost[position])
        bundle.putString("flag","orderInfo")
        recipeDialog.arguments = bundle
        recipeDialog.show(mainActivity.supportFragmentManager,"recipeDialog")
    }
}