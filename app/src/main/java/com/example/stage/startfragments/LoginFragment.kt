package com.example.stage.startfragments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.ServerConnection.AccountApi
import com.example.stage.ServerConnection.Login
import com.example.stage.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment: Fragment() {
    lateinit var startActivity: StartActivity

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view: View = inflater.inflate(R.layout.login_fragment,container,false)
        startActivity = activity as StartActivity


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var logInBtn = view.findViewById<Button>(R.id.login_login_button)
        var transaction = parentFragmentManager.beginTransaction()
        var loginEditTextView = view.findViewById<EditText>(R.id.login_input_id)
        var pwdEditTextView = view.findViewById<EditText>(R.id.login_input_password)
        val startConstraintLayout by lazy{requireActivity().findViewById<ConstraintLayout>(R.id.start_constraintlayout)}
        val startBackBtn by lazy { startConstraintLayout.findViewById<ImageButton>(R.id.start_back_btn) }

        //뒤로가기버튼
        startBackBtn.setOnClickListener{
            startBackBtn.visibility = View.INVISIBLE
            transaction.remove(this).commit()
            parentFragmentManager.popBackStack("login", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        //로그인 버튼
        logInBtn.setOnClickListener{

            // API와 통신
            startActivity.requestAccountApi.getLogin(loginEditTextView.text.toString(),pwdEditTextView.text.toString()).enqueue(object : Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.d("dd","cc")
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    Log.d("dd","dd")
                    var intent = Intent(activity, MainActivity::class.java)
                    intent.putExtra("id",loginEditTextView.text.toString())
                    startActivity(intent)
                    startActivity.finish()
                }
            }
            )
        }

    }


}