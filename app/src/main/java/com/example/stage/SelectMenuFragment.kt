package com.example.stage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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
            parentFragmentManager.beginTransaction().replace(R.id.mainpage_fragment_container_view,MenuFragment()).commit()
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