package com.example.stage.mainfragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R

class MenuFragment : Fragment(){
    var coffeeMenuList : ArrayList<Menu> = arrayListOf(
        Menu("아메리카노","Americano","3200","americano"),
        Menu("아포가토","Affogato","4500","menu_coffee_affogato_original"),
        Menu("카페라떼","Caffe Latte","3700","menu_coffee_cafe_latte"),
        Menu("카페모카","Caffe Moca","3900","menu_coffee_cafe_moca"),
        Menu("카푸치노","Cappuccino","4000","menu_coffee_cappuchino"),
        Menu("카라멜 마끼야또","Caramel Macchiato","4500","menu_coffee_caramel_macchiato"),
        Menu("에스프레소","Espresso","3800","menu_coffee_espresso"),
        Menu("바닐라 라떼","VanillaLatte","4000","menu_coffee_vanilla_latte")

    )
    var beverageMenuList : ArrayList<Menu> = arrayListOf(
        Menu("초콜렛","Chocolate","3200","menu_beverage_chocolate"),
        Menu("생강차","Ginger Tea","4500","menu_beverage_ginger_tea"),
        Menu("녹차라떼","Green Tea Latte","3700","menu_beverage_greantea_latte"),
        Menu("딸기라떼","Strawberry Latte","3900","menu_beverage_ice_strawberry_latte"),
        Menu("쌍화차","Ssanghwa Tea","4500","menu_beverage_ssanghwa_tee"),
        Menu("고구마라떼","Sweetpotato Latte","4400","menu_beverage_sweetpotato_latte"),
        Menu("토피넛라떼","Topeanut Latte","4200","menu_beverage_topeanut_latte")
    )

    var blendingTeaMenuList : ArrayList<Menu> = arrayListOf(
        Menu("복분자 뱅쇼","BokBunJa Vin Chaud","3800","menu_blending_tea_bokbunja_vin_chaud"),
        Menu("유자차","Citron Tea","3800","menu_blending_tea_citron_tea"),
        Menu("생딸기 히비스커스","Strawberry Hibiscus","4500","menu_blending_tea_fresh_strawberry_peach_hibiscuss"),
        Menu("자몽차","Grapefruit Tea","3900","menu_blending_tea_grapefruit"),
        Menu("제주 감귤차","Jeju Tangerine Tea","4600","menu_blending_tea_grapefruit"),
        Menu("레몬차","Lemon Tea","3800","menu_blending_tea_lemon_tea"),
        Menu("석류 오리지널","Pomegranate Tea","3900","menu_blending_tea_pomegranate_tea")
    )

    var flatchinoMenuList : ArrayList<Menu> = arrayListOf(
        Menu("블루베리요거트 플랫치노","Blueberry Yogurt Flatccino","4800","menu_flatchino_blueberry_yogurt_flatccino"),
        Menu("커피 플랫치노","Coffee Flatccino","4500","menu_flatchino_coffe_flatccino"),
        Menu("자몽 플랫치노","Grapefruit Flatccino","3700","menu_flatchino_grapefruit_flatccino"),
        Menu("망고 플랫치노","Mango Flatccino","3900","menu_flatchino_mango_flatccino"),
        Menu("모카 플랫치노","Moca Flatccino","3200","menu_flatchino_mocha_flatccino"),
        Menu("복숭아 플랫치노","Peach Flatccino","3200","menu_flatchino_peach_flatccino"),
        Menu("플레인 요거트 플랫치노","Plain Yogurt Flatccino","3200","menu_flatchino_plain_yogurt_flatccino"),
        Menu("딸기 요거트 플랫치노","Strawberry Yogurt Flatccino","3800","menu_flatchino_strawberry_yogurt_flatchino")
    )

    var iceFlakeMenuList : ArrayList<Menu> = arrayListOf(
        Menu("망고샤베트 빙수","Mango Sherbet Flake","3200","menu_ice_flake_mango_sherbet_snow_flake"),
        Menu("망고요거트 빙수","Mango Yogurt Flake","4500","menu_ice_flake_mango_yogurt_ice_flake"),
        Menu("팥빙수","Redbean Ice Flake","3700","menu_ice_flake_redbean_ice_flakes"),
        Menu("인절미 팥빙수","Injeolmi Ice Flake","3900","menu_ice_flake_redbean_injeolmi_ice_flake"),
        Menu("딸기 치즈 빙수","Strawberry Cheese flake","3200","menu_ice_flake_strawberry_cheese_flake")
    )

    var shakeAndAdeMenuList : ArrayList<Menu> = arrayListOf(
        Menu("초콜렛 쿠키 쉐이크","Chocolate Cookie Shake","3200","menu_shake_ade_chocolate_cookie_shake"),
        Menu("석류 에이드","Grapefruit Ade","4500","menu_shake_ade_grapefruit_ade"),
        Menu("청포도 에이드","Green Grape Ade","3700","menu_shake_ade_green_grape_ade"),
        Menu("레몬 에이드","Lemon Ade","3900","menu_shake_ade_lemon_ade"),
        Menu("오리지널 쉐이크","Origin Shake","3200","menu_shake_ade_origin_shake"),
        Menu("딸기 쉐이크","Strawberry Shake","3200","menu_shake_ade_strawberry_shake")
    )

    //베이커리 메뉴
    var breadMenuList : ArrayList<Menu> = arrayListOf(
        Menu("초코렛 브라우니","Chocolate Brownie","3200","menu_bread_chocolate_brownie"),
        Menu("크로아상","Croissant","4500","menu_bread_croissant"),
        Menu("생딸기 연유 브레드","Strawberry Condensed Milk Bread","3700","menu_bread_fresh_strawberry_condenced_milk_bread"),
        Menu("딸기 크로플","Strawberry Croiffle","3900","menu_bread_fresh_strawberry_croiffle"),
        Menu("딸기 와플","Strawberry Waffle","3200","menu_bread_fresh_strawberry_waffle"),
        Menu("햄치즈 샌드위치","Ham Cheese Sandwich","3200","menu_bread_ham_cheese_sandwich")
    )
    var cookieAndEtcMenuList : ArrayList<Menu> = arrayListOf(
        Menu("까르보나라 구운 주먹밥","Carbonara Grilled Riceball","3200","menu_cookie_etc_carbonara_grilled_riceball"),
        Menu("매콤로제 주먹밥","Spicy Rose Riceball","3200","menu_cookie_etc_spicy_rose_riceball"),
        Menu("초콜렛청크 쿠키","Chocolate Chunk Cookie","4500","menu_cookie_etc_chocolate_chunk_cookie"),
        Menu("화이트초코 쿠키","White Chocolate Cookie","3200","menu_cookie_etc_white_chocolate_macadamia_cookie"),
        Menu("콘 수프","Corn Soup","3700","menu_cookie_etc_corn_soup"),
        Menu("양송이 수프","Mushroom Soup","3900","menu_cookie_etc_mushroom_soup")
    )
    var dessertMenuList : ArrayList<Menu> = arrayListOf(
        Menu("크림치즈 머핀","Cream Cheese Muffin","3200","menu_dessert_cream_cheese_muffin"),
        Menu("꿀호떡","Honey Hotteok","4500","menu_dessert_honey_hotteok"),
        Menu("마스카포네 티라미수","Mascarpone Tiramisu","3700","menu_dessert_mascarpone_tiramisu"),
        Menu("수플레 치즈 케이크","Souffle Cheese Cake","3900","menu_dessert_souffle_cheese_cake"),
        Menu("딸기 치즈 마카롱","Strawberry Cheese Macaron","3200","menu_dessert_strawberry_cheese_macaron"),
        Menu("바닐라 마카롱","Vanilla Macaron","3200","menu_dessert_vanilla_macaron")
    )

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.menu_fragment,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mainActivity = activity as MainActivity
        var selectedMenu = arguments?.getInt("title")
        var isDrink = arguments?.getBoolean("isChecked")
        //카테고리 이름 설정
        var categoryName = view.findViewById<TextView>(R.id.menu_category_name)
        categoryName.text = arguments?.getString("categoryName")


        //넘겨받은 값으로 선택한 카테고리 인식
        var showList =
            when(setOf(selectedMenu,isDrink)){
                setOf(0,true) -> coffeeMenuList
                setOf(1,true) -> beverageMenuList
                setOf(2,true) -> blendingTeaMenuList
                setOf(3,true) -> flatchinoMenuList
                setOf(4,true) -> iceFlakeMenuList
                setOf(5,true) -> shakeAndAdeMenuList
                setOf(0,false) -> breadMenuList
                setOf(1,false) -> dessertMenuList
                setOf(2,false) -> cookieAndEtcMenuList
                else -> coffeeMenuList
            }

        //메뉴 리사이클러뷰 설정
        var menuAdapter = MenuRVAdapter(mainActivity,showList)

        var menuRecyclerView = view.findViewById<RecyclerView>(R.id.menu_recyclerview)
        menuRecyclerView.adapter = menuAdapter
        menuRecyclerView.setHasFixedSize(true)


        //뒤로가기 버튼
        var menuBackBtn = view.findViewById<ImageButton>(R.id.menu_back_button)
        menuBackBtn.setOnClickListener {
            mainActivity.removeFragment(this)
        }

        //장바구니 버튼
        var menuBasketBtn = view.findViewById<ImageButton>(R.id.menu_basket_button)
        menuBasketBtn.setOnClickListener {
            mainActivity.openBasket()
        }
    }



}