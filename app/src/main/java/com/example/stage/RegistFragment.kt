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

class RegistFragment: Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.registration_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var registBackBtn = view.findViewById<ImageButton>(R.id.regist_back_btn)
        var transaction = parentFragmentManager.beginTransaction()
        var registIdCheckBtn = view.findViewById<Button>(R.id.registration_id_check)
        var registBtn = view.findViewById<Button>(R.id.registration_regist_button)


        //뒤로가기버튼
        registBackBtn.setOnClickListener{
            transaction.replace(R.id.start_fragment_container_view,StartFragment()).commit()
        }

        //중복체크 버튼
        registIdCheckBtn.setOnClickListener{
            var builder = AlertDialog.Builder(activity)
            builder.setMessage("사용가능한 아이디입니다.")
                    .setPositiveButton("확인",DialogInterface.OnClickListener{
                            dialogInterface, i->
                })
            builder.show()
        }
        
        //회원가입 버튼
        registBtn.setOnClickListener{
            var builder2 = AlertDialog.Builder(activity)
            builder2.setMessage("회원가입 완료")
                .setPositiveButton("확인",DialogInterface.OnClickListener{
                    dialogInterface, i ->  transaction.replace(R.id.start_fragment_container_view,StartFragment()).commit()
                })
            builder2.show()
        }

    }
}