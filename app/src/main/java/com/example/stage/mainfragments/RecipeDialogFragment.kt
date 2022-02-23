package com.example.stage.mainfragments


import android.content.Context
import android.os.Bundle
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
    lateinit var basketList : ArrayList<Bundle>
    var totalCost = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = activity as MainActivity
        basketList = arguments?.getParcelableArrayList("basketList")!!
        totalCost = arguments?.getInt("totalCost")!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.recipe_dialog,container,false)

//        크기설정
        dialog?.setContentView(R.layout.recipe_dialog)
        dialog?.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog!!.setCancelable(false)

//        리사이클 뷰 설정
        var recipeRVAdapter = RecipeRVAdapter(mainActivity,basketList)
        var recipeRecyclerView = view.findViewById<RecyclerView>(R.id.recipe_recyclerview)
        recipeRecyclerView.adapter = recipeRVAdapter
        recipeRecyclerView.setHasFixedSize(true)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recipeExitBtn = view.findViewById<ImageButton>(R.id.recipe_exit_button)
        var recipeTotalCostView = view.findViewById<TextView>(R.id.recipe_total_cost)
        var recipePaymentCostView = view.findViewById<TextView>(R.id.recipe_payment_cost)

        recipeTotalCostView.text = totalCost.toString()
        recipePaymentCostView.text = totalCost.toString()

        // X버튼
        recipeExitBtn.setOnClickListener {
            dismiss()
            mainActivity.replaceFragment(CategoryFragment())
            mainActivity.resetBasket()
        }

    }
}