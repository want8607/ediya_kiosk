package com.example.stage.mainfragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.stage.MainActivity
import com.example.stage.R
//해야할것  값 유지 되도록 액티비티에서 값을 저장해야함, 또 결제창에서 리사이클 뷰 써야함, 다이얼로그에 값 전달
class SelectMenuFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.selectmenu_fragment,container,false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mainActivity = activity as MainActivity
        var menuCost: Int? = arguments?.getString("menuCost")?.toInt()
        var menuNum = 1
        var size  = "Tall"
        var selectedCup = "매장컵"
        var shotNum = 0
        var syrupNum = 0
        var isHotOrIce = "Hot"
        var shotMinusBtn = view.findViewById<ImageButton>(R.id.selectmenu_shot_minus_button)
        var shotPlusBtn = view.findViewById<ImageButton>(R.id.selectmenu_shot_plus_button)
        var shotNumTextView = view.findViewById<TextView>(R.id.selectmenu_shot_num_textview)
        var hotIceRadioGroup = view.findViewById<RadioGroup>(R.id.hot_ice_radio_group)
        var hotRadioBtn = view.findViewById<RadioButton>(R.id.hot_radio_button)
        var selectMenuBackBtn = view.findViewById<ImageButton>(R.id.selectmenu_back_button)
        var totalCostTextView = view.findViewById<TextView>(R.id.selectedmenu_menu_total_cost)
        var sizeRadioGroup = view.findViewById<RadioGroup>(R.id.selectmenu_size_radio_group)
        var tallSizeButton = view.findViewById<RadioButton>(R.id.selectmenu_tall_radio_button)
        var cupRadioGroup = view.findViewById<RadioGroup>(R.id.selectmenu_cup_choice_radio_group)
        var storeCupBtn = view.findViewById<RadioButton>(R.id.selectmenu_store_cup)
        var syrupMinusBtn = view.findViewById<ImageButton>(R.id.selectmenu_syrup_minus_button)
        var syrupPlusBtn = view.findViewById<ImageButton>(R.id.selectmenu_syrup_plus_button)
        var syrupNumTextView = view.findViewById<TextView>(R.id.selectmenu_syrup_num_textview)
        var menuMinusBtn = view.findViewById<ImageButton>(R.id.selectmenu_menu_minus_button)
        var menuPlusBtn = view.findViewById<ImageButton>(R.id.selectmenu_menu_plus_button)
        var menuNumTextView = view.findViewById<TextView>(R.id.selectmenu_menu_num_textview)
        var selectMenuAddBtn = view.findViewById<Button>(R.id.selectmenu_add_button)
        var selectMenuBasketBtn = view.findViewById<ImageButton>(R.id.selectmenu_basket_button)
        var selectedMenuConstraintLayout = view.findViewById<ConstraintLayout>(R.id.selectedmenu_constraint)

        selectedMenuConstraintLayout.setPadding(0,0,0,mainActivity.navigationHeight())




        //선택된 메뉴 값에 따라 바인드
        view.findViewById<TextView>(R.id.selectedmenu_menu_name).text = arguments?.getString("menuName")
        view.findViewById<TextView>(R.id.selectedmenu_menu_english_name).text = arguments?.getString("menuEnglishName")
        view.findViewById<TextView>(R.id.selectedmenu_menu_cost).text = arguments?.getString("menuCost")
        val resourceId = mainActivity.resources.getIdentifier(arguments?.getString("menuImg"), "drawable", mainActivity.packageName)
        view.findViewById<ImageView>(R.id.selectedmenu_menu_img).setImageResource(resourceId)

        //총금액 설정

        totalCostTextView.text = menuCost.toString()

        //뒤로가기
        selectMenuBackBtn.setOnClickListener {
            mainActivity.removeFragment(this)
        }

        //핫 아이스
        hotRadioBtn.isChecked = true
        hotIceRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.hot_radio_button-> {isHotOrIce = "Hot"}
                R.id.ice_radio_button-> {isHotOrIce = "Ice"}
            }
        }

        //사이즈 변경 버튼 이벤트
        tallSizeButton.isChecked = true
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
        storeCupBtn.isChecked = true
        cupRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.selectmenu_store_cup->{selectedCup = "매장컵"}
                R.id.selectmenu_personal_cup->{selectedCup = "개인컵"}
                R.id.selectmenu_disposable_cup->{selectedCup = "일회용컵"}
            }
        }

        //샷 플러스마이너스 버튼 클릭이벤트
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
            mainActivity.basketService.setBasket(bundle)  //서비스에 값 전달
            mainActivity.basketService.updateTotalMenuNum()//총 메뉴 개수,액수 변경
            //알림 표시
            var builder = AlertDialog.Builder(activity)
            builder.setCancelable(false)
            builder.setMessage("장바구니에 추가 되었습니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener{
                        dialogInterface, i-> mainActivity.replaceFragment(CategoryFragment())
                })
            builder.show()
        }

        //장바구니 버튼
        selectMenuBasketBtn.setOnClickListener {
            mainActivity.openBasket()
        }

    }

}