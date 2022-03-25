package com.example.stage.mainfragments.maindialog

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.ServerConnection.OrderHistoryDatas
import com.example.stage.mainfragments.mainRVAdapter.RecipeRVAdapter


class RecipeDialogFragment: DialogFragment() {
    lateinit var mainActivity: MainActivity
    lateinit var orderMenuList : OrderHistoryDatas
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.recipe_dialog,container,false)
        mainActivity = activity as MainActivity
//        크기설정
        dialog?.setContentView(R.layout.recipe_dialog)
        dialog?.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog!!.setCancelable(false)

        var orderNum = arguments?.getInt("seq")!!
        orderMenuList = mainActivity.historyDatas[orderNum]

        Log.d("ff",orderMenuList.toString())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeExitBtn = view.findViewById<ImageButton>(R.id.recipe_exit_button)
        val recipeOrderNumView = view.findViewById<TextView>(R.id.recipe_order_num)
        recipeOrderNumView.text =arguments?.getInt("seq")!!.toString()

        //        리사이클 뷰 설정
        val recipeRVAdapter = RecipeRVAdapter(mainActivity,orderMenuList.order_list)
        val recipeRecyclerView = view.findViewById<RecyclerView>(R.id.recipe_recyclerview)
        recipeRecyclerView.adapter = recipeRVAdapter
        recipeRecyclerView.setHasFixedSize(true)
        val totalCost = orderMenuList.total_price
        val recipeTotalCostView = view.findViewById<TextView>(R.id.recipe_total_cost)
        val recipePaymentCostView = view.findViewById<TextView>(R.id.recipe_payment_cost)
        recipeTotalCostView.text = totalCost.toString()
        recipePaymentCostView.text = totalCost.toString()

        // X버튼
        recipeExitBtn.setOnClickListener {
            var flag = arguments?.getString("flag")
            if(flag == "orderInfo"){
                dismiss()
            }else{
                dismiss()
                while (mainActivity.supportFragmentManager.backStackEntryCount != 0) {
                    mainActivity.supportFragmentManager.popBackStackImmediate();
                }

            }

        }
    }

}