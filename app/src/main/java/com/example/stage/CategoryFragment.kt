package com.example.stage

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class CategoryFragment : Fragment(){
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.category_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //초기 카테고리 선택
        var categoryRadioBtn = view.findViewById<RadioButton>(R.id.category_button1)
        categoryRadioBtn.isChecked = true

        //리스트 누르면 다음 menu_fragment 로 넘어가기
        var categoryList1 = view.findViewById<ConstraintLayout>(R.id.category_list1)

        categoryList1.setOnClickListener{
            parentFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,MenuFragment()).addToBackStack(null).commit()
        }

        //장바구니 보여주기
        var categoryBasketBtn = view.findViewById<ImageButton>(R.id.category_basket_button)
        categoryBasketBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().add(R.id.mainpage_fragment_container_view,BasketFragment()).addToBackStack(null).commit()
        }
        
        //카테고리 삽입하기
        var categorylist = resources.getTextArray(R.array.drink)

        //틀 레이아웃
        var categoryListLinearLayout = view.findViewById<LinearLayout>(R.id.category_list_scroll_linearlayout)

    }
    //dp를 pixel로 바꿔주는 함수
    fun dpTopix(context: Context, dp: Float): Int{
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }
    //레이아웃 넣기
    fun addLiearView(){

        //바깥쪽 리니어
        var newCategoryOuterLinearLayout = LinearLayout(activity)
        newCategoryOuterLinearLayout.orientation = LinearLayout.HORIZONTAL
        var paramNewCategoryOuterLinearLayout = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
        )

        // 이미지
        var newCategoryImgView = ImageView(activity)
        newCategoryImgView.background = resources.getDrawable(R.drawable.category_background,null)
        newCategoryImgView.setImageResource(R.drawable.americano)
        var paramImgView = LinearLayout.LayoutParams(
            dpTopix(requireActivity(),resources.getDimension(R.dimen.category_picture_width)),
            dpTopix(requireActivity(),resources.getDimension(R.dimen.category_picture_height))
        )
        paramImgView.setMargins(20,0,0,0)
        newCategoryOuterLinearLayout.addView(newCategoryImgView,paramImgView)

        //안쪽 리니어
        var newCategoryInnerLinearLayout = LinearLayout(activity)
        newCategoryInnerLinearLayout.orientation = LinearLayout.VERTICAL
        var paramNewCategoryInnerLinearLayout = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT
        )

        //카테고리 이름
        var newCategoryName = TextView(activity)
        newCategoryName.text = "커피"
        newCategoryName.textSize = 26f
        newCategoryName.font
    }
}