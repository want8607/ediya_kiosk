package com.example.stage
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class StartFragment: Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {


        var view: View = inflater.inflate(R.layout.start_fragment,container,false)
        var startRegistBtn = view.findViewById<Button>(R.id.start_regist_button)
        var startLoginBtn = view.findViewById<Button>(R.id.start_login_button)
        var startBackBtn = getView()?.findViewById<ImageButton>(R.id.start_back_btn)
        var transaction : FragmentTransaction =  parentFragmentManager.beginTransaction()

        startLoginBtn!!.setOnClickListener {
           transaction.replace(R.id.start_fragment_container_view, LoginFragment()).commit()
            if (startBackBtn != null) {
                startBackBtn.setVisibility((View.VISIBLE))
            }
        }
        startRegistBtn!!.setOnClickListener {
            transaction.replace(R.id.start_fragment_container_view, RegistFragment()).commit()
            if (startBackBtn != null) {
                startBackBtn.setVisibility((View.VISIBLE))
            }
        }
        startBackBtn?.setOnClickListener{
            transaction.replace(R.id.start_fragment_container_view,StartFragment()).commit()
            if (startBackBtn != null) {
                startBackBtn.setVisibility((View.VISIBLE))
            }
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}