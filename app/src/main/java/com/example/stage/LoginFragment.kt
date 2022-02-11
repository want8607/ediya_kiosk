package com.example.stage
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class LoginFragment: Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view: View = inflater.inflate(R.layout.login_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var logInBtn = view.findViewById<Button>(R.id.login_login_button)
        var transaction = parentFragmentManager.beginTransaction()
        val startConstraintLayout by lazy{requireActivity().findViewById<ConstraintLayout>(R.id.start_constraintlayout)}
        val startBackBtn by lazy { startConstraintLayout.findViewById<ImageButton>(R.id.start_back_btn) }
        //뒤로가기버튼
        startBackBtn.setOnClickListener{
            startBackBtn.visibility = View.INVISIBLE
            transaction.remove(this).commit()
        }

        //로그인 버튼
        logInBtn.setOnClickListener{
            //엑티비티 바꿔줘야함
            var intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
        }

    }
}