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
import com.example.stage.ServerConnection.OrderHistory
import com.example.stage.ServerConnection.OrderHistoryData
import com.example.stage.ServerConnection.OrderHistoryDatas
import com.example.stage.mainfragments.mainInterface.OrderInfoItemClick
import com.example.stage.mainfragments.mainRVAdapter.OrderInfoRVAdapter
import com.example.stage.mainfragments.maindialog.RecipeDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class OrderInfoFragment : Fragment(), OrderInfoItemClick{
    lateinit var mainActivity: MainActivity
    lateinit var orderInfoRecyclerView: RecyclerView
    lateinit var orderInfoRVAdapter: OrderInfoRVAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.order_info_fragment,container,false)

        CoroutineScope(Dispatchers.Main).launch {

            mainActivity.historyDatas = async(Dispatchers.IO) {
                mainActivity.requestOrderApi.getOrderHistorySuspend(mainActivity.userId).data
            }.await()

            orderInfoRVAdapter = OrderInfoRVAdapter(mainActivity,mainActivity.historyDatas,this@OrderInfoFragment)
            orderInfoRecyclerView = view.findViewById<RecyclerView>(R.id.order_info_recyclerview)
            orderInfoRecyclerView.adapter = orderInfoRVAdapter
            orderInfoRecyclerView.setHasFixedSize(true)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var orderInfoBackBtn = view.findViewById<ImageButton>(R.id.order_info_back_button)
        orderInfoBackBtn.setOnClickListener {
            mainActivity.removeFragment(this,"orderInfo")
        }
    }

    override fun onclick(fragment: RecipeDialogFragment, seq:Int) {
        var bundle = Bundle()
        bundle.putInt("seq",seq)
        bundle.putString("flag","orderInfo")//ok
        fragment.arguments = bundle
        fragment.show(mainActivity.supportFragmentManager,"recipeDialog")
    }
}