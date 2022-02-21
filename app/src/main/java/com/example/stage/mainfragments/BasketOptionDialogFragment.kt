package com.example.stage.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.stage.MainActivity
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

        var mainActivity = activity as MainActivity
        var position = arguments?.getInt("position")
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
        var completeBtn = view.findViewById<Button>(R.id.basket_option_change_complete_button)
        var isHotOrIce = basketHotOrIce
        var cup = basketCup
        var size = basketSize
        var shotNum = basketShotNum?.toInt()
        var syrupNum = basketSyrupNum?.toInt()
        var optionChangCost = 0

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
                R.id.basket_option_tall_radio_button->{
                    if (size == "Grande"){
                        optionChangCost = optionChangCost.minus(1000)
                    }else if (size == "Venti"){
                        optionChangCost = optionChangCost.minus(1500)
                    }
                    size = "Tall"
                }
                R.id.basket_option_grande_radio_button->{
                    if (size == "Tall"){
                        optionChangCost = optionChangCost.plus(1000)
                    }else if (size == "Venti"){
                        optionChangCost = optionChangCost.minus(500)
                    }
                    size = "Grande"
                }
                R.id.basket_option_Venti_radio_button->{
                    if (size == "Tall"){
                        optionChangCost = optionChangCost.plus(1500)
                    }else if (size == "Grande") {
                        optionChangCost = optionChangCost?.plus(500)
                    }
                    size = "Venti"
                }
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
                R.id.basket_option_store_cup->{cup = "매장컵"}
                R.id.basket_option_personal_cup->{cup = "개인컵"}
                R.id.basket_option_disposable_cup->{cup = "일회용컵"}
            }
        }

        //shotNum
        shotNumTextView.text = basketShotNum
        shotMinusBtn.setOnClickListener {
            if(shotNum!! > 0){
                shotNum = shotNum?.minus(1)
                optionChangCost =optionChangCost.minus(500)
                shotNumTextView.text = shotNum.toString()
            }
        }
        shotPlusBtn.setOnClickListener {
            if(shotNum!! < 9){
                shotNum = shotNum?.plus(1)
                optionChangCost =optionChangCost.plus(500)
                shotNumTextView.text = shotNum.toString()
            }
        }
        //syrupNum
        syrupNumTextView.text = basketSyrupNum
        syrupMinusBtn.setOnClickListener {
            if(syrupNum!! > 0){
                syrupNum -= 1
                syrupNumTextView.text = syrupNum.toString()
            }
        }
        syrupPlusBtn.setOnClickListener {
            if(syrupNum!! < 9){
                syrupNum += 1
                syrupNumTextView.text = syrupNum.toString()
            }
        }

        //수정완료
        completeBtn.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("position",position!!)
            bundle.putString("basketHotOrIce",isHotOrIce)
            bundle.putString("basketSize",size)
            bundle.putString("basketCup",cup)
            bundle.putString("basketShotNum", shotNum.toString())
            bundle.putString("basketSyrupNum",syrupNum.toString())
            bundle.putString("optionChangCost", optionChangCost.toString())
            mainActivity.basket.changeOption(bundle)
            this.dismiss()
        }
    }
}