package com.example.stage.mainfragments


import android.content.Context
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


class RecipeDialogFragment: DialogFragment() {
    lateinit var mainActivity: MainActivity
    lateinit var orderMenuList : ArrayList<ArrayList<String>>
    var orderNum = 0
    override fun onAttach(context: Context) {
        super.onAttach(context)
        orderNum = arguments?.getInt("seq")!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.recipe_dialog,container,false)
        mainActivity = activity as MainActivity
//        db 불러오기
        val value = arrayListOf(
            arrayListOf("seq",orderNum.toString())
        )
        orderMenuList = mainActivity.databaseControl.readData(mainActivity.readableDb,"orderMenus",value)

//        크기설정
        dialog?.setContentView(R.layout.recipe_dialog)
        dialog?.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog!!.setCancelable(false)

//        리사이클 뷰 설정
        val recipeRVAdapter = RecipeRVAdapter(mainActivity,orderMenuList)
        val recipeRecyclerView = view.findViewById<RecyclerView>(R.id.recipe_recyclerview)
        recipeRecyclerView.adapter = recipeRVAdapter
        recipeRecyclerView.setHasFixedSize(true)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeExitBtn = view.findViewById<ImageButton>(R.id.recipe_exit_button)
        val recipeTotalCostView = view.findViewById<TextView>(R.id.recipe_total_cost)
        val recipePaymentCostView = view.findViewById<TextView>(R.id.recipe_payment_cost)
        val recipeOrderNumView = view.findViewById<TextView>(R.id.recipe_order_num)
        val totalCost = calTotalCost()
        recipeTotalCostView.text = totalCost.toString()
        recipePaymentCostView.text = totalCost.toString()
        recipeOrderNumView.text = orderNum.toString()
        
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

    fun calTotalCost(): Int{
        var totalCost = 0
        for(i in orderMenuList.indices) {
            totalCost += (orderMenuList[i][2].toInt() * orderMenuList[i][3].toInt())
        }
        return totalCost
    }
}