package com.example.stage

import java.lang.NumberFormatException

class Common {
    fun getNumber(haveRange: Boolean,range: Int): Int{
        while(true){
            try {
                var num :Int? = 0
                num = readLine()!!.toInt()

                if((haveRange) and (num>range)){
                    throw IndexOutOfBoundsException()
                }
                return num
                break
            } catch (e: NumberFormatException){
                println("올바른 값을 입력하세요.:")
            } catch (e:IndexOutOfBoundsException){
                println("범위안의 번호를 입력하세요.:")
            }
        }
    }

    fun getString(): String{
        while(true){
            try {
                var str :String? = ""
                str = readLine()!!
                if (str ==""){
                    throw IllegalArgumentException()
                }
                return str
                break
            } catch (e:IllegalArgumentException){
                println("올바른 문자를 입력하세요.:")
            }
        }
    }
}