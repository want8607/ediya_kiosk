package com.example.stage.startfragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stage.R
import com.example.stage.StartActivity

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
        val registReInputPwd = view.findViewById<EditText>(R.id.reigstration_repassword)
        val startBackBtn = startActivity.findViewById<ImageButton>(R.id.start_back_btn)

        //뒤로가기버튼
        startBackBtn.setOnClickListener{
            startBackBtn.visibility = View.INVISIBLE
            transaction.replace(R.id.start_fragment_container_view, StartFragment()).commit()
        }

        //중복체크 버튼
        registIdCheckBtn.setOnClickListener{
            startActivity.requestAccountApi.idDuplicateCheck()
//            var text = ""
//            var value = arrayListOf<ArrayList<String>>(
//                arrayListOf("id",registInputId.text.toString())
//            )
//            if (registInputId.text.toString()==""){
//                text = getString(R.string.toast_put_id_on)
//                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
//            }else if(startActivity.databaseControl.readData(startActivity.readableDb,"account",value).size == 0){
//                text = getString(R.string.toast_available_id)
//                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
//            }else{
//                text = getString(R.string.toast_not_available_id)
//                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
//            }
        }
        
        //회원가입 버튼
        registBtn.setOnClickListener{
            var text = ""
            var value = arrayListOf(
                arrayListOf("id",registInputId.text.toString())
            )
            //비번 입력 오류
            if(registInputPwd.text.isBlank()){
                text = getString(R.string.toast_put_pw_on)
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }else if(!(registInputPwd.text.toString()).equals(registReInputPwd.text.toString())){
                text = getString(R.string.toast_check_pw)
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }else if(startActivity.databaseControl.readData(startActivity.readableDb,"account",value).size != 0){
                text = getString(R.string.toast_check_id)
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }else {
                //데이터 베이스에 등록
                val data = arrayListOf(
                    arrayListOf("id",registInputId.text.toString(),"TEXT"),
                    arrayListOf("pw",registInputPwd.text.toString(),"TEXT"))
                startActivity.databaseControl.createData(
                    startActivity.writableDb,"account",data
                )
                startBackBtn.visibility = View.INVISIBLE
                transaction.remove(this ).commit()
                parentFragmentManager.popBackStack("regist", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                text = getString(R.string.toast_complete_regist)
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}