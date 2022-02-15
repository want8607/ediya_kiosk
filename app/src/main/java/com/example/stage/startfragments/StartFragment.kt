package com.example.stage.startfragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.stage.R

class StartFragment: Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view: View = inflater.inflate(R.layout.start_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var startRegistBtn = view.findViewById<Button>(R.id.start_regist_button)
        var startLoginBtn = view.findViewById<Button>(R.id.start_login_button)
        val startConstraintLayout by lazy{requireActivity().findViewById<ConstraintLayout>(R.id.start_constraintlayout)}
        val startBackBtn by lazy { startConstraintLayout.findViewById<ImageButton>(R.id.start_back_btn) }


        startLoginBtn!!.setOnClickListener {
            startBackBtn.visibility = View.VISIBLE
            parentFragmentManager.beginTransaction().add(R.id.start_fragment_container_view, LoginFragment()).addToBackStack(null).commit()
        }
        startRegistBtn!!.setOnClickListener {
            startBackBtn.visibility = View.VISIBLE
            parentFragmentManager.beginTransaction().add(R.id.start_fragment_container_view, RegistFragment()).addToBackStack(null).commit()
        }

    }

}