package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.mainInterface.OrderInfoItemClick
import kotlin.math.log

class OrderInfoFragment : Fragment(), OrderInfoItemClick{
    lateinit var mainActivity: MainActivity
    lateinit var orderStorage: ArrayList<Bundle> //[bundle]->"orderList"parcelable/"totalCost"Int/"paymentTime"String/"orderNumber"Int
    lateinit var orderInfoRecyclerView: RecyclerView
    lateinit var orderInfoRVAdapter: OrderInfoRVAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
        orderStorage = arguments?.getParcelableArrayList("orderStorage")!! //4
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.order_info_fragment,container,false)
        orderInfoRVAdapter = OrderInfoRVAdapter(mainActivity,orderStorage,this)
        orderInfoRecyclerView = view.findViewById<RecyclerView>(R.id.order_info_recyclerview)
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
        var basketList : ArrayList<Bundle> = orderStorage[position].getParcelableArrayList("orderList")!!
        bundle.putParcelableArrayList("basketList",basketList)
        bundle.putInt("orderNumber",orderStorage[position].getInt("orderNumber"))
        bundle.putInt("totalCost",orderStorage[position].getInt("totalCost"))
        bundle.putString("flag","orderInfo")//ok
        recipeDialog.arguments = bundle
        recipeDialog.show(mainActivity.supportFragmentManager,"recipeDialog")
    }
}