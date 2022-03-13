package com.example.stage.mainfragments.mainInterface

import androidx.fragment.app.DialogFragment

interface OnItemClick {
    fun onClick(position : Int,value:String,totalCost: String)
    fun onOptionClick(fragment: DialogFragment)
    fun onDeleteClick(position: Int)
}