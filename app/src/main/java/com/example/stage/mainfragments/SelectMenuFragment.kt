package com.example.stage.mainfragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.stage.MainActivity
import com.example.stage.R
//해야할것  3. 장바구니 리사이클 뷰 만들어서 값 전달, 값 유지 되도록 액티비티에서 값을 저장해야함, 또 결제창에서 리사이클 뷰 써야함, 다이얼로그에 값 전달
class SelectMenuFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.selectmenu_fragment,container,false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mainActivity = activity as MainActivity

        //선택된 메뉴 값에 따라 바인드
        view.findViewById<TextView>(R.id.selectedmenu_menu_name).text = arguments?.getString("menuName")
        view.findViewById<TextView>(R.id.selectedmenu_menu_english_name).text = arguments?.getString("menuEnglishName")
        view.findViewById<TextView>(R.id.selectedmenu_menu_cost).text = arguments?.getString("menuCost")
        val resourceId = mainActivity.resources.getIdentifier(arguments?.getString("menuImg"), "drawable", mainActivity.packageName)
        view.findViewById<ImageView>(R.id.selectedmenu_menu_img).setImageResource(resourceId)

        //총금액 설정
        var menuCost: Int? = arguments?.getString("menuCost")?.toInt()
        var menuNum : Int= 1
        var totalCostTextView = view.findViewById<TextView>(R.id.selectedmenu_menu_total_cost)
        totalCostTextView.text = menuCost.toString()

        //뒤로가기
        var selectMenuBackBtn = view.findViewById<ImageButton>(R.id.selectmenu_back_button)
        selectMenuBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        //핫 아이스
        var hotIceRadioGroup = view.findViewById<RadioGroup>(R.id.hot_ice_radio_group)
        lateinit var isHotOrIce : String
        hotIceRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.hot_radio_button-> {isHotOrIce = "Hot"}
                R.id.ice_radio_button-> {isHotOrIce = "Ice"}
            }
        }

        //사이즈 변경 버튼 이벤트
        var sizeRadioGroup = view.findViewById<RadioGroup>(R.id.selectmenu_size_radio_group)
        var tallSizeButton = view.findViewById<RadioButton>(R.id.selectmenu_tall_radio_button)
        tallSizeButton.isChecked = true
        var size : String = "Tall"
        lateinit var lastselected : String
        sizeRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.selectmenu_tall_radio_button->{
                    if (size == "Grande"){
                        menuCost = menuCost?.minus(1000)
                    }else if (size == "Venti"){
                        menuCost = menuCost?.minus(1500)
                    }
                    totalCostTextView.text = (menuCost?.times(menuNum)).toString()
                    size = "Tall"
                }
                R.id.selectmenu_grande_radio_button->{
                    if (size == "Tall"){
                        menuCost = menuCost?.plus(1000)
                    }else if (size == "Venti"){
                        menuCost = menuCost?.minus(500)
                    }
                    totalCostTextView.text = (menuCost?.times(menuNum)).toString()
                    size = "Grande"
                }
                R.id.selectmenu_Venti_radio_button->{
                    if (size == "Tall"){
                        menuCost = menuCost?.plus(1500)
                    }else if (size == "Grande") {
                        menuCost = menuCost?.plus(500)
                    }
                    totalCostTextView.text = (menuCost?.times(menuNum)).toString()
                    size = "Venti"
                }
            }
        }

        //컵선택
        var cupRadioGroup = view.findViewById<RadioGroup>(R.id.selectmenu_cup_choice_radio_group)
        var storeCupBtn = view.findViewById<RadioButton>(R.id.selectmenu_store_cup)
        lateinit var selectedCup : String
        cupRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.selectmenu_store_cup->{selectedCup = "매장컵"}
                R.id.selectmenu_personal_cup->{selectedCup = "개인컵"}
                R.id.selectmenu_disposable_cup->{selectedCup = "일회용컵"}
            }
            Log.d("message",selectedCup)
        }

        //샷 플러스마이너스 버튼 클릭이벤트
        var shotMinusBtn = view.findViewById<ImageButton>(R.id.selectmenu_shot_minus_button)
        var shotPlusBtn = view.findViewById<ImageButton>(R.id.selectmenu_shot_plus_button)
        var shotNumTextView = view.findViewById<TextView>(R.id.selectmenu_shot_num_textview)
        var shotNum: Int = 0

        shotMinusBtn.setOnClickListener {

            if(shotNum > 0){
                shotNum -= 1
                menuCost =menuCost?.minus(500)
                shotNumTextView.text = shotNum.toString()
                totalCostTextView.text = (menuCost?.times(menuNum)).toString()
            }
        }

        shotPlusBtn.setOnClickListener {
            if(shotNum < 9){
                shotNum += 1
                menuCost = menuCost?.plus(500)
                shotNumTextView.text = shotNum.toString()
                totalCostTextView.text = (menuCost?.times(menuNum)).toString()
            }
        }

        //시럽 플러스 마이너스 버튼 클릭이벤트
        var syrupMinusBtn = view.findViewById<ImageButton>(R.id.selectmenu_syrup_minus_button)
        var syrupPlusBtn = view.findViewById<ImageButton>(R.id.selectmenu_syrup_plus_button)
        var syrupNumTextView = view.findViewById<TextView>(R.id.selectmenu_syrup_num_textview)
        var syrupNum = 0

        syrupMinusBtn.setOnClickListener {
            if(syrupNum > 0){
                syrupNum -= 1
                syrupNumTextView.text = syrupNum.toString()
            }
        }

        syrupPlusBtn.setOnClickListener {
            if(syrupNum < 9){
                syrupNum += 1
                syrupNumTextView.text = syrupNum.toString()
            }
        }

        //메뉴 플러스 마이너스 버튼 클릭이벤트
        var menuMinusBtn = view.findViewById<ImageButton>(R.id.selectmenu_menu_minus_button)
        var menuPlusBtn = view.findViewById<ImageButton>(R.id.selectmenu_menu_plus_button)
        var menuNumTextView = view.findViewById<TextView>(R.id.selectmenu_menu_num_textview)

        menuMinusBtn.setOnClickListener {
            if(menuNum > 1){
                menuNum-=1
                menuNumTextView.text = menuNum.toString()
                totalCostTextView.text = (menuCost?.times(menuNum)).toString()
            }
        }

        menuPlusBtn.setOnClickListener {
            if(menuNum < 20){
                menuNum+=1
                menuNumTextView.text = menuNum.toString()
                totalCostTextView.text = (menuCost?.times(menuNum)).toString()
            }
        }

        //장바구니에 추가
        var selectMenuAddBtn = view.findViewById<Button>(R.id.selectmenu_add_button)
        selectMenuAddBtn.setOnClickListener {

            // 설정들을 장바구니에 보내야함
            var bundle = Bundle()
            bundle.putString("basketName",arguments?.getString("menuName"))
            bundle.putString("basketEnglishName",arguments?.getString("menuEnglishName"))
            bundle.putString("basketHotOrIce",isHotOrIce)
            bundle.putString("basketShotNum",shotNum.toString())
            bundle.putString("basketSyrupNum",syrupNum.toString())
            bundle.putString("basketSize",size)
            bundle.putString("basketCup",selectedCup)
            bundle.putString("basketMenuNum",menuNum.toString())
            bundle.putString("basketMenuCost",menuCost.toString())
            bundle.putString("basketTotalCost", totalCostTextView.text.toString())
            bundle.putString("photo",arguments?.getString("menuImg"))
            mainActivity.sendDataToBasket(bundle)
            bundle.getString("photo")?.let { it1 -> Log.d("messeage", it1) }
            //알림 표시
            var builder = AlertDialog.Builder(activity)
            builder.setMessage("장바구니에 추가 되었습니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener{
                        dialogInterface, i-> mainActivity.replaceFragment(CategoryFragment())
                })
            builder.show()
        }

        //장바구니 버튼
        var selectMenuBasketBtn = view.findViewById<ImageButton>(R.id.selectmenu_basket_button)
        selectMenuBasketBtn.setOnClickListener {
            mainActivity.addFragment(BasketFragment())
        }

    }

}