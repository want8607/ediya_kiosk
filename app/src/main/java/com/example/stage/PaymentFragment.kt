package com.example.stage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class PaymentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.payment_fragment,container,false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var paymentBackBtn = view.findViewById<ImageButton>(R.id.payment_back_button)
        paymentBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        //결제하기
        var paymentPayBtn = view.findViewById<Button>(R.id.payment_pay_button)
        paymentPayBtn.setOnClickListener {
            var recipeDialog = RecipeDialogFragment()
            recipeDialog.show(parentFragmentManager,"recipeDialog")
        }
    }
}