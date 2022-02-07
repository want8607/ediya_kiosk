package com.example.stage
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class LoginFragment: Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view: View = inflater.inflate(R.layout.login_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var logBackBtn = view.findViewById<ImageButton>(R.id.login_back_btn)
        var logInBtn = view.findViewById<Button>(R.id.login_login_button)
        var transaction = parentFragmentManager.beginTransaction()

        //뒤로가기 버튼
        logBackBtn.setOnClickListener{
            transaction.replace(R.id.start_fragment_container_view,StartFragment()).commit()
        }
        //로그인 버튼
        logInBtn.setOnClickListener{
            //엑티비티 바꿔줘야함
            var intent = Intent(activity,SecondActivity::class.java)//여기 유지보수
            startActivity(intent)
        }

    }
}