package com.example.stage.week1

class Manager(menu: Menu) {
    var password: Int = 1111
    var menu: Menu = menu
    var managerMenuList: List<String> = listOf("메뉴수정","매출현황조회","관리자모드 종료")
    var changeMenuList: List<String> = listOf("메뉴추가","메뉴이름/가격변경","메뉴삭제","뒤로가기")
    var escape:Boolean = true
    var totalSale: Int = 0


    fun checkManagerMode(number: Int?){                 //비밀번호 체크
        if(number == password){
            menu.onManagerMode = true
            menu.basket.initializeBasket()
            managerMode()
        }
    }

    fun managerMode(){                                          //관리자모드 루프
        while (escape){
            showManagerMenu()
            selectManagerMenu()
        }
        menu.onManagerMode = false
        escape = true
    }

    fun showManagerMenu(){                                              //관리자 메뉴 표시
        println("========관리자모드========")
        for (i in managerMenuList.indices){
            println("${i+1}.${managerMenuList[i]}")
        }
        println("========================")
    }

    fun selectManagerMenu(){
        println("선택:")
        var selectedMenuNum: Int? = menu.basket.common.getNumber(true,3)

        if (selectedMenuNum == 1){                                      //메뉴수정 선택
            println("========================")
            for (i in changeMenuList.indices){
                println("${i+1}.${changeMenuList[i]}")
            }
            println("========================")
            selectedMenuNum = menu.basket.common.getNumber(true,4)
            if (selectedMenuNum == 1){                //메뉴추가
                addMenu()
            }else if(selectedMenuNum == 2){           // 메뉴이름/가격 수정
                changeMenu()
            }else if(selectedMenuNum == 3){           //메뉴삭제
                deleteMenu()
            }else if(selectedMenuNum ==4){            //뒤로가기
            }

        }else if(selectedMenuNum == 2){                                 //매출조회 선택
            showSales()
        }else if(selectedMenuNum == 3){                                 //관리자모드 종료

            escape = false
        }
    }

    fun addMenu(){
        menu.showCategory()
        println("카테고리선택:")
        var selectedCategoryNum: Int? = menu.basket.common.getNumber(true,menu.category.size)
        println("메뉴이름:")
        var newMenuName : String = menu.basket.common.getString()
        println("가격:")
        var newMenuCost : String = menu.basket.common.getNumber(false,0).toString()
        var newMenu: MutableList<String?> = mutableListOf(newMenuName,newMenuCost)
        menu.category[selectedCategoryNum!!-1].add(newMenu)
        menu.showMenu(selectedCategoryNum)
    }

    fun changeMenu() {
        menu.showCategory()
        println("카테고리선택:")
        var selectedCategoryNum: Int? = menu.basket.common.getNumber(true,menu.category.size)
        menu.showMenu(selectedCategoryNum)
        println("변경할 메뉴를 선택하세요")
        var selectedChangeMenuNum: Int?= menu.basket.common.getNumber(true,menu.category[selectedCategoryNum!!-1].size)
        println("메뉴이름:")
        var newMenuName : String = menu.basket.common.getString()
        println("가격:")
        var newMenuCost : String = menu.basket.common.getNumber(false,0).toString()
        menu.category[selectedCategoryNum!! - 1][selectedChangeMenuNum!! - 1][0] = newMenuName
        menu.category[selectedCategoryNum!! - 1][selectedChangeMenuNum!! - 1][1] = newMenuCost
        menu.showMenu(selectedCategoryNum)
    }

    fun deleteMenu(){
        menu.showCategory()
        println("카테고리선택:")
        var selectedCategoryNum: Int? = menu.basket.common.getNumber(true,menu.category.size)
        menu.showMenu(selectedCategoryNum)
        println("삭제할 메뉴를 선택하세요")
        var selectedChangeMenuNum: Int? = menu.basket.common.getNumber(true,menu.category[selectedCategoryNum!!-1].size)
        menu.category[selectedCategoryNum!!-1].removeAt(selectedChangeMenuNum!!-1)
        menu.showMenu(selectedCategoryNum)
    }

    fun addSales(sale: Int){
        totalSale += sale
    }

    fun showSales(){
        println("총매출:${totalSale}")
    }

}




