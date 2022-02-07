package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        var categoryBtn = findViewById<RadioButton>(R.id.category_button1)
        categoryBtn.isChecked = true

    }

}
