package com.example.stage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class CategoryFragment : Fragment(){
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.category_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //초기 카테고리 선택
        var categoryRadioBtn = view.findViewById<RadioButton>(R.id.category_button1)
        categoryRadioBtn.isChecked = true

        //리스트 누르면 다음 menu_fragment 로 넘어가기
        var categoryList1 = view.findViewById<ConstraintLayout>(R.id.category_list1)

        categoryList1.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,MenuFragment()).commit()
        }

        //장바구니 보여주기
        var categoryBasketBtn = view.findViewById<ImageButton>(R.id.category_basket_button)
        categoryBasketBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,BasketFragment()).addToBackStack(null).commit()
        }
    }
}