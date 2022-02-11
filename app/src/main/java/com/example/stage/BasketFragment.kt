package com.example.stage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class BasketFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.basket_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var basketBackBtn = view.findViewById<ImageButton>(R.id.basket_back_button)
        basketBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
        //메뉴 플러스 마이너스 버튼 이벤트
        var menuMinusBtn = view.findViewById<ImageButton>(R.id.basket_menu_minus_button)
        var menuPlusBtn = view.findViewById<ImageButton>(R.id.basket_menu_plus_button)
        var menuNumTextView = view.findViewById<TextView>(R.id.basket_menu_num_textview)

        menuMinusBtn.setOnClickListener {
            var menuNum = Integer.parseInt(menuNumTextView.text.toString())
            if(menuNum > 0){
                menuNumTextView.text = (menuNum-1).toString()
            }
        }

        menuPlusBtn.setOnClickListener {
            var menuNum = Integer.parseInt(menuNumTextView.text.toString())
            if(menuNum < 20){
                menuNumTextView.text = (menuNum+1).toString()
            }
        }
        //결제버튼 눌렀을 때
        var basketPayBtn = view.findViewById<Button>(R.id.basket_payment_button)
        basketPayBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,PaymentFragment()).addToBackStack(null).commit()
        }
    }
}