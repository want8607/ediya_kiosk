package com.example.stage.week1

import java.lang.NumberFormatException
import kotlin.IndexOutOfBoundsException

class Menu(basket: Basket) {
    var basket: Basket = basket
    var coffee: MutableList<MutableList<String?>> = mutableListOf( //커피카테고리
        mutableListOf("아메리카노","3200"),
        mutableListOf("카페라떼","3700"),
        mutableListOf("바닐라라떼","3900"),
        mutableListOf("카푸치노","3700"))

    var beverage: MutableList<MutableList<String?>> = mutableListOf( //베버리지카테고리
        mutableListOf("초콜릿","3700"),
        mutableListOf("민트초콜릿","4000"),
        mutableListOf("토피넛라떼","4000"))

    var blending: MutableList<MutableList<String?>> = mutableListOf( //블렌딩카테고리
        mutableListOf("자몽네이블 오렌지","4200"),
        mutableListOf("석류 애플라임","4200"),
        mutableListOf("제주청귤 블라썸","4200"))

    var category: MutableList<MutableList<MutableList<String?>>> = mutableListOf(coffee,beverage,blending)

    var categoryName : MutableList<String> = mutableListOf("커피","베버리지","블렌딩")
    var selectedCategoryNum: Int? = 0
    var selectedMenuNum: Int? = 0
    var maxMenuNum: Int = 0
    var onManagerMode: Boolean = false
    var password : Int = 0
    init{

    }

    fun showCategory(){                 //카테고리 보여주기
        println("====이디야 커피====")
        for (index_category in categoryName.indices){
            println("${index_category+1}.${categoryName[index_category]}")
        }
        println("==============")
        if(!onManagerMode) {
            selectCategory()
        }
    }

    fun selectCategory(){
        println("카테고리를 선택하세요 : ")
        while(true){
            try {
                selectedCategoryNum = readLine()!!.toInt()
                if((selectedCategoryNum!!>category.size)and(selectedCategoryNum != password)){
                    throw IndexOutOfBoundsException()
                }
                break
            } catch (e: NumberFormatException){
                println("올바른 값을 입력하세요.:")
            } catch (e:IndexOutOfBoundsException){
                println("범위안의 번호를 입력하세요.:")
            }
        }
    }


    fun showMenu(selected_categoryNum: Int?) {   //메뉴 보여주기
        println("==============")
        for(i in category[selected_categoryNum!!-1].indices){
            println("${i+1}.${category[selected_categoryNum!!-1][i][0]}-${category[selected_categoryNum!!-1][i][1]}")
            maxMenuNum = i+1
        }
        if(!onManagerMode){
            println("${maxMenuNum+1}.뒤로가기")
        }
        println("==============")
        if(!onManagerMode){
            selectMenu()
        }
    }

    fun selectMenu(){

        println("메뉴를 선택하세요 : ")
        selectedMenuNum = basket.common.getNumber(true,maxMenuNum+1)

        if (selectedMenuNum != maxMenuNum + 1) { //뒤로가기가 아니면 개수입력
            println("개수를 입력하세요 : ")
            var numberOfMenu: Int? = basket.common.getNumber(false,0)
            basket.addToBasket(category[selectedCategoryNum!!-1][selectedMenuNum!!.toInt() - 1][0]!!, numberOfMenu, category[selectedCategoryNum!!-1][selectedMenuNum!!.toInt() - 1][1]!!.toInt())
        }
    }
}
