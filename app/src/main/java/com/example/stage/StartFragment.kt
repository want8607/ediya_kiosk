package com.example.stage
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

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
        var transaction: FragmentTransaction = parentFragmentManager.beginTransaction()

        startLoginBtn!!.setOnClickListener {
            startBackBtn.visibility = View.VISIBLE
            transaction.replace(R.id.start_fragment_container_view, LoginFragment()).commit()
        }
        startRegistBtn!!.setOnClickListener {
            startBackBtn.visibility = View.VISIBLE
            transaction.replace(R.id.start_fragment_container_view, RegistFragment()).commit()
        }

    }

}