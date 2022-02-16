package com.example.stage.mainfragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stage.MainActivity
import com.example.stage.R
//해야할것 1. 샷 500원 추가 2.컵 사이즈 커지면 1000원 1500원 추가, 3. 장바구니 리사이클 뷰 만들어서 값 전달, 값 유지 되도록 액티비티에서 값을 저장해야함, 또 결제창에서 리사이클 뷰 써야함, 다이얼로그에 값 전달
class SelectMenuFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.selectmenu_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mainActivity = activity as MainActivity

        //뒤로가기
        var selectMenuBackBtn = view.findViewById<ImageButton>(R.id.selectmenu_back_button)
        selectMenuBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        //선택된 메뉴 값에 따라 바인드
        view.findViewById<TextView>(R.id.selectedmenu_menu_name).text = arguments?.getString("menuName")
        view.findViewById<TextView>(R.id.selectedmenu_menu_english_name).text = arguments?.getString("menuEnglishName")
        view.findViewById<TextView>(R.id.selectedmenu_menu_cost).text = arguments?.getString("menuCost")
        val resourceId = mainActivity.resources.getIdentifier(arguments?.getString("menuImg"), "drawable", mainActivity.packageName)
        view.findViewById<ImageView>(R.id.selectedmenu_menu_img).setImageResource(resourceId)


        //샷 플러스마이너스 버튼 클릭이벤트
        var shotMinusBtn = view.findViewById<ImageButton>(R.id.selectemenu_shot_minus_button)
        var shotPlusBtn = view.findViewById<ImageButton>(R.id.selectemenu_shot_plus_button)
        var shotNumTextView = view.findViewById<TextView>(R.id.selectmenu_shot_num_textview)

        shotMinusBtn.setOnClickListener {
            var shotNum = Integer.parseInt(shotNumTextView.text.toString())
            if(shotNum > 0){
            shotNumTextView.text = (shotNum-1).toString()
            }
        }

        shotPlusBtn.setOnClickListener {
            var shotNum = Integer.parseInt(shotNumTextView.text.toString())
            if(shotNum < 9){
                shotNumTextView.text = (shotNum+1).toString()
            }
        }

        //시럽 플러스 마이너스 버튼 클릭이벤트
        var syrupMinusBtn = view.findViewById<ImageButton>(R.id.selectemenu_syrup_minus_button)
        var syrupPlusBtn = view.findViewById<ImageButton>(R.id.selectemenu_syrup_plus_button)
        var syrupNumTextView = view.findViewById<TextView>(R.id.selectmenu_syrup_num_textview)

        syrupMinusBtn.setOnClickListener {
            var syrupNum = Integer.parseInt(syrupNumTextView.text.toString())
            if(syrupNum > 0){
                syrupNumTextView.text = (syrupNum-1).toString()
            }
        }

        syrupPlusBtn.setOnClickListener {
            var syrupNum = Integer.parseInt(syrupNumTextView.text.toString())
            if(syrupNum < 9){
                syrupNumTextView.text = (syrupNum+1).toString()
            }
        }

        //메뉴 플러스 마이너스 버튼 클릭이벤트
        var menuMinusBtn = view.findViewById<ImageButton>(R.id.selectmenu_menu_minus_button)
        var menuPlusBtn = view.findViewById<ImageButton>(R.id.selectmenu_menu_plus_button)
        var menuNumTextView = view.findViewById<TextView>(R.id.selectmenu_menu_num_textview)

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

        //장바구니에 추가
        var selectMenuAddBtn = view.findViewById<Button>(R.id.selectmenu_add_button)
        selectMenuAddBtn.setOnClickListener {
            var builder = AlertDialog.Builder(activity)
            builder.setMessage("장바구니에 추가 되었습니다.")
                .setPositiveButton("확인", DialogInterface.OnClickListener{
                        dialogInterface, i-> parentFragmentManager.beginTransaction().replace(
                    R.id.mainpage_fragment_container_view,
                    CategoryFragment()
                ).commit()
                })
            builder.show()
        }
        //장바구니 버튼
        var selectMenuBasketBtn = view.findViewById<ImageButton>(R.id.selectmenu_basket_button)
        selectMenuBasketBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().add(
                R.id.mainpage_fragment_container_view,
                BasketFragment()
            ).addToBackStack(null).commit()
        }
    }

}