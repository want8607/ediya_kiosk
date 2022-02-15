package com.example.stage.week1

class Kiosk() {
    var basket : Basket = Basket()
    var menu : Menu = Menu(basket)
    var manager : Manager = Manager(menu)
    var payment : Payment = Payment(basket,manager)


    init {
        menu.password = manager.password
        mainloop()
    }

    fun mainloop() {
        while (true) {
            menu.showCategory()
            manager.checkManagerMode(menu.selectedCategoryNum)

            if (menu.selectedCategoryNum!! <= menu.category.size) {
                menu.showMenu(menu.selectedCategoryNum)
                if (menu.selectedMenuNum == menu.maxMenuNum + 1) {
                    //뒤로가기
                } else {
                    basket.showBasket()
                    subloop()
                }
            }
        }
    }

    fun subloop() {
        while (true) {
            println("음식추가:1, 수정:2, 결제:3 눌러주세요.")
            var subloopNum: Int?  = basket.common.getNumber(true, 3)
            if (subloopNum == 1) { //음식추가 -> 카테고리로
                break
            } else if (subloopNum == 2) { //장바구니 수정
                basket.updateBasket()
                basket.showBasket()
            } else if (subloopNum == 3) { //계산
                payment.askToPay()
                break
            }
        }
    }

}



fun main(){
    var kiosk= Kiosk()
}
