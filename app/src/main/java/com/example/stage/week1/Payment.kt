package com.example.stage.week1

import java.util.concurrent.TimeUnit

class Payment(basket: Basket, manager: Manager) {

    var cashOrCard: Int? = 0
    var inputMoney: Int = 0
    var basket: Basket = basket
    var manager: Manager = manager
    var orderNum: Int = 1


    fun askToPay(){
        println("현금결제:1 카드결제:2")
        cashOrCard = basket.common.getNumber(true,2)
        if (cashOrCard == 1){
            payWithCash(basket.totalCost)
        }else if(cashOrCard == 2){
            payWithCard(basket.totalCost)
        }
        endPayment()
    }

    fun payWithCash(totalCost: Int){
        inputMoney = 0
        println("금액을 넣으세요:")
        while (true) {
            inputMoney += basket.common.getNumber(false,0)
            if (totalCost > inputMoney) {
                println("${totalCost-inputMoney}원을 더 넣으세요")
            }else{
                break
            }
        }
    }

    fun payWithCard(totalCost: Int){
        println("총가격:${totalCost}")
        println("카드삽입해주세요")
        TimeUnit.SECONDS.sleep(2)
    }

    fun endPayment(){
        println("결제완료")
        printRecipe()
        manager.addSales(basket.totalCost)
        basket.initializeBasket()
    }

    fun printRecipe(){
        println("====================================")
        println("===============영수증================")
        if(cashOrCard == 1) {//현금 or 카드
            basket.showBasket()
            println("               현금:${inputMoney}원")
            println("               거스름돈:${inputMoney-basket.totalCost}원")
            println("               대기번호:${orderNum}            ")
        }else if(cashOrCard ==2){
            basket.showBasket()
            println("               결제금액:${basket.totalCost}원")
            println("               대기번호:${orderNum}")
        }
        println("====================================")
        orderNum += 1
    }

}