package com.example.stage.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.stage.R

class BasketOptionDialogFragment: DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.basket_option_change_dialog,container,false)

        //크기설정
        dialog?.setContentView(R.layout.recipe_dialog)
        dialog?.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog!!.setCancelable(false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var basketHotOrIce = arguments?.getString("basketHotOrIce")
        var basketSize = arguments?.getString("basketSize")
        var basketCup = arguments?.getString("basketCup")
        var basketShotNum = arguments?.getString("basketShotNum")
        var basketSyrupNum = arguments?.getString("basketSyrupNum")
        var backBtn = view.findViewById<ImageButton>(R.id.basket_option_exit_button)
        var hotOrIceGroup = view.findViewById<RadioGroup>(R.id.basket_option_hot_ice_radio_group)
        var hotBtn = view.findViewById<RadioButton>(R.id.basket_option_hot_radio_button)
        var iceBtn = view.findViewById<RadioButton>(R.id.basket_option_ice_radio_button)
        var sizeGroup = view.findViewById<RadioGroup>(R.id.basket_option_size_radio_group)
        var tallBtn = view.findViewById<RadioButton>(R.id.basket_option_tall_radio_button)
        var grandeBtn = view.findViewById<RadioButton>(R.id.basket_option_grande_radio_button)
        var ventiBtn = view.findViewById<RadioButton>(R.id.basket_option_Venti_radio_button)
        var cupGroup = view.findViewById<RadioGroup>(R.id.basket_option_cup_choice_radio_group)
        var storeCupBtn = view.findViewById<RadioButton>(R.id.basket_option_store_cup)
        var personalCupBtn = view.findViewById<RadioButton>(R.id.basket_option_personal_cup)
        var disposableCupBtn = view.findViewById<RadioButton>(R.id.basket_option_disposable_cup)
        var shotPlusBtn = view.findViewById<ImageButton>(R.id.basket_option_shot_plus_button)
        var shotMinusBtn = view.findViewById<ImageButton>(R.id.basket_option_shot_minus_button)
        var shotNumTextView = view.findViewById<TextView>(R.id.basket_option_shot_num_textview)
        var syrupPlusBtn = view.findViewById<ImageButton>(R.id.basket_option_syrup_plus_button)
        var syrupMinusBtn = view.findViewById<ImageButton>(R.id.basket_option_syrup_minus_button)
        var syrupNumTextView = view.findViewById<TextView>(R.id.basket_option_syrup_num_textview)
        lateinit var isHotOrIce : String
        lateinit var size : String
        lateinit var cup : String
        lateinit var shotNum : String
        lateinit var syrupNum : String
        //뒤로가기
        backBtn.setOnClickListener {
            this.dismiss()
        }

        //Hot or Ice
        if(basketHotOrIce == "Hot"){
            hotBtn.isChecked = true
        }else{
            iceBtn.isChecked = true
        }

        hotOrIceGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.basket_option_hot_radio_button-> {isHotOrIce = "Hot"}
                R.id.basket_option_ice_radio_button-> {isHotOrIce = "Ice"}
            }
        }

        //size
        when(basketSize){
            "Tall"->{tallBtn.isChecked = true}
            "Grande"->{grandeBtn.isChecked = true}
            "Venti"->{ventiBtn.isChecked = true}
        }

        sizeGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.basket_option_tall_radio_button->{size = "Tall"}
                R.id.basket_option_grande_radio_button->{size = "Grande"}
                R.id.basket_option_Venti_radio_button->{size = "Venti"}
            }
        }

        //cup
        when(basketCup){
            "매장컵"->{storeCupBtn.isChecked = true}
            "개인컵"->{personalCupBtn.isChecked = true}
            "일회용컵"->{disposableCupBtn.isChecked = true}
        }
        cupGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.basket_option_store_cup->{size = "매장컵"}
                R.id.basket_option_personal_cup->{size = "개인컵"}
                R.id.basket_option_disposable_cup->{size = "일회용컵"}
            }
        }

        //shotNum
        shotNumTextView.text = basketShotNum

        shotMinusBtn.setOnClickListener {
            var oriShotNum = (shotNumTextView.text.toString()).toInt()
            if(oriShotNum > 0){
                shotNum = (oriShotNum - 1).toString()
                shotNumTextView.text = shotNum
            }
        }
        shotPlusBtn.setOnClickListener {
            var oriShotNum = (shotNumTextView.text.toString()).toInt()
            if(oriShotNum < 9){
                shotNum = (oriShotNum + 1).toString()
                shotNumTextView.text = shotNum
            }
        }
        //syrupNum
        syrupNumTextView.text = basketSyrupNum
    }
}