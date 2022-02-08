package com.example.stage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MenuFragment : Fragment(){
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.menu_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //메뉴선택시 세부선택 창으로 넘어가기
        var menuList1 = view.findViewById<ConstraintLayout>(R.id.menu_list1)
        menuList1.setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,SelectMenuFragment()).commit()
        }

        //뒤로가기 버튼
        var menuBackBtn = view.findViewById<ImageButton>(R.id.menu_back_button)
        menuBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,CategoryFragment()).commit()
        }

        //장바구니 버튼
        var menuBasketBtn = view.findViewById<ImageButton>(R.id.menu_basket_button)
        menuBasketBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,BasketFragment()).addToBackStack(null).commit()
        }
    }



}