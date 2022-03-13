package com.example.stage.mainfragments.mainInterface

import com.example.stage.mainfragments.maindialog.RecipeDialogFragment

interface OrderInfoItemClick {
    fun onclick(fragment: RecipeDialogFragment, position:Int)
}