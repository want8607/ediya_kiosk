package com.example.stage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment

class AlterDialogFragment(var listener :()-> Unit) : DialogFragment() {
    init {

    }
    lateinit var question: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.alter_dialog,container,false)

        //크기설정
        dialog?.setContentView(R.layout.alter_dialog)
        dialog?.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false)
        view.findViewById<TextView>(R.id.alter_dialog_textview).text = question
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var yesBtn = view.findViewById<Button>(R.id.yesBtn)
        yesBtn.setOnClickListener {
            listener()
        }

    }

    fun setText(text: String){
        this.question = text
    }
}