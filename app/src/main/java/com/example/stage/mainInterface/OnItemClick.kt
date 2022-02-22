package com.example.stage.mainInterface

import androidx.fragment.app.DialogFragment

interface OnItemClick {
    fun onClick(position : Int,value:String,totalCost: String)
    fun onOptionClick(fragment: DialogFragment)
    fun onDeleteClick(position: Int)
}