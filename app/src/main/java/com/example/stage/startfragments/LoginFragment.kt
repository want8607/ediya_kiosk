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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stage.MainActivity
import com.example.stage.R
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
        val logInBtn = view.findViewById<Button>(R.id.login_login_button)
        val transaction = parentFragmentManager.beginTransaction()
        val loginEditTextView = view.findViewById<EditText>(R.id.login_input_id)
        val pwdEditTextView = view.findViewById<EditText>(R.id.login_input_password)
        val startBackBtn = startActivity.findViewById<ImageButton>(R.id.start_back_btn)

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
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if(response.body()!!.success){
                    var intent = Intent(activity, MainActivity::class.java)
                    intent.putExtra("id",loginEditTextView.text.toString())
                    startActivity(intent)
                    startActivity.finish()
                    }
                }
            }
            )
        }

    }


}