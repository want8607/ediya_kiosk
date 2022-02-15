package com.example.stage.week1


class Basket {
    var common = Common()
    var basketLists: MutableList<MutableList<String?>> = mutableListOf()
    var basketList: MutableList<String?> = mutableListOf()
    var totalCost: Int = 0

    fun addToBasket(name: String, numOfMenu: Int?, cost: Int){
        basketList.add(name)
        basketList.add(numOfMenu.toString())
        basketList.add(cost.toString())
        basketLists.add(basketList)
        basketList = mutableListOf()
    }

    fun showBasket(){
        totalCost = 0                       //totalCost 초기화
        println("====================================\n" +
                "메뉴이름         개수            가격")
        for(i in basketLists.indices) {
            println("${basketLists[i][0]}       ${basketLists[i][1]}       ${basketLists[i][1]!!.toInt()*basketLists[i][2]!!.toInt()}원")
            totalCost += basketLists[i][1]!!.toInt()*basketLists[i][2]!!.toInt()
        }
        println("====================================")
        println("               총금액:${totalCost}원")
    }

    fun updateBasket(){

        println("============================")
        println("몇번째 항목을 수정하시겠습니까?")
        var updateMenuNumber: Int? = common.getNumber(true,basketLists.size)
        println("삭제: 1, 수량변경: 2")
        var delOrUpdate: Int? = common.getNumber(true,2)

        if(delOrUpdate == 1){ //삭제
            basketLists.removeAt(updateMenuNumber!!-1)

        }else if(delOrUpdate == 2){ //수량변경
            println("몇개로 변경하시겠습니까?")
            var updatedNum: Int? = common.getNumber(false,0)
            basketLists[updateMenuNumber!!-1].set(1,updatedNum.toString())
        }
    }

    fun initializeBasket(){
        basketLists = mutableListOf()
        basketList  = mutableListOf()
        totalCost = 0
    }

}