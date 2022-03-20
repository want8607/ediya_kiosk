package com.example.stage.startfragments
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stage.R
import com.example.stage.ServerConnection.IdDuplicate
import com.example.stage.ServerConnection.SignUp
import com.example.stage.ServerConnection.UserData
import com.example.stage.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistFragment: Fragment() {
    lateinit var startActivity: StartActivity
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.registration_fragment,container,false)
        startActivity = activity as StartActivity
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction = parentFragmentManager.beginTransaction()
        val registInputId = view.findViewById<EditText>(R.id.registration_input_id)
        val registIdCheckBtn = view.findViewById<Button>(R.id.registration_id_check)
        val registBtn = view.findViewById<Button>(R.id.registration_regist_button)
        val registInputPwd = view.findViewById<EditText>(R.id.registration_password)
        val registReInputPwd = view.findViewById<EditText>(R.id.registration_repassword)
        val registInputName = view.findViewById<EditText>(R.id.registration_name)
        val registInputContact = view.findViewById<EditText>(R.id.registration_contact)
        val startBackBtn = startActivity.findViewById<ImageButton>(R.id.start_back_btn)


        //뒤로가기버튼
        startBackBtn.setOnClickListener{
            startBackBtn.visibility = View.INVISIBLE
            transaction.replace(R.id.start_fragment_container_view, StartFragment()).commit()
        }

        //중복체크 버튼
        registIdCheckBtn.setOnClickListener{
            startActivity.requestAccountApi.idDuplicateCheck(registInputId.text.toString()).enqueue(object : Callback<IdDuplicate> {
                override fun onResponse(call: Call<IdDuplicate>, response: Response<IdDuplicate>) {
                    var text = ""
                    val body = response.body()!!
                    if (registInputId.text.toString()==""){//공백
                        text = getString(R.string.toast_put_id_on)
                        Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
                    }else if(body.success){  //성공
                        text = getString(R.string.toast_available_id)
                        Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
                    }else if(!body.success){   //중복
                        text = getString(R.string.toast_not_available_id)
                        Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<IdDuplicate>, t: Throwable) {
                }
            })
        }
        
        //회원가입 버튼
        registBtn.setOnClickListener{
            startActivity.requestAccountApi.postSignUp(
                UserData(registInputId.text.toString(),
                registInputPwd.text.toString(),
                registInputName.text.toString(),
                registInputContact.text.toString())).enqueue(object : Callback<SignUp>{

                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    var text = ""
                    val body = response.body()!!
                    //타입체커 넣어야함
                    if(body.success){ //성공
                        startBackBtn.visibility = View.INVISIBLE
                        transaction.remove(this@RegistFragment ).commit()
                        parentFragmentManager.popBackStack("regist", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        text = getString(R.string.toast_complete_regist)
                        Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Log.d("regist","fail")
                }

            })
        }
    }
}