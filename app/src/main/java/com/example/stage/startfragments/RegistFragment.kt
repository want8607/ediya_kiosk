package com.example.stage.startfragments
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
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
        val startConstraintLayout by lazy{requireActivity().findViewById<ConstraintLayout>(R.id.start_constraintlayout)}
        val startBackBtn by lazy { startConstraintLayout.findViewById<ImageButton>(R.id.start_back_btn) }

        //뒤로가기버튼
        startBackBtn.setOnClickListener{
            startBackBtn.visibility = View.INVISIBLE
            transaction.replace(R.id.start_fragment_container_view, StartFragment()).commit()
        }

        //중복체크 버튼
        registIdCheckBtn.setOnClickListener{
            var text = ""
            var value = arrayListOf<ArrayList<String>>(
                arrayListOf("id",registInputId.text.toString())
            )
            if (registInputId.text.toString()==""){
                text = "아이디를 입력해 주세요."
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }else if(startActivity.databaseControl.readData(startActivity.readableDb,"account",value).size == 0){
                text = "사용가능한 아이디 입니다."
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }else{
                text = "이미 사용중인 아이디 입니다."
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }
        }
        
        //회원가입 버튼
        registBtn.setOnClickListener{
            var text = ""
            var value = arrayListOf(
                arrayListOf("id",registInputId.text.toString())
            )
            //비번 입력 오류
            if (!(registInputPwd.text.toString()).equals(registReInputPwd.text.toString())){
                text = "비밀번호 확인을 다시 해주세요"
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }else if(startActivity.databaseControl.readData(startActivity.readableDb,"account",value).size != 0){
                text = "아이디를 다시 입력해주세요."
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
                text = "회원가입 완료"
                Toast.makeText(startActivity, text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}