package com.example.stage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class SelectMenuFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.selectmenu_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var selectMenuBackBtn = view.findViewById<ImageButton>(R.id.selectmenu_back_button)
        selectMenuBackBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
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
                        dialogInterface, i-> parentFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,CategoryFragment()).commit()
                })
            builder.show()
        }
        //장바구니 버튼
        var selectMenuBasketBtn = view.findViewById<ImageButton>(R.id.selectmenu_basket_button)
        selectMenuBasketBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,BasketFragment()).addToBackStack(null).commit()
        }
    }

}