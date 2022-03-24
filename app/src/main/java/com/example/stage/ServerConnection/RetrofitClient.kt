package com.example.stage.ServerConnection


import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body

//무조건 리턴값 가져야하고 객체여야함
object RetrofitClient{
    fun initRetrofit(): Retrofit{

        val url = "http://52.79.157.214:3000" // 서버주소
        val gson = Gson()                   // 서버와 주고받을 형식
        val clientBuilder = OkHttpClient.Builder().build()//http통신 규약을 사용

        val connection = Retrofit.Builder()
            .baseUrl(url)
            .client(clientBuilder)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return  connection
    }
}


//회원가입
data class  SignUp(val message: String, val success: Boolean)
data class  IdDuplicate(val message: String, val success: Boolean)
    // post
data class UserData(val id: String, val pw: String, val name: String, val contact : String )

//로그인
data class  Login(val message: String, val success: Boolean)

//카테고리
data class  Category(val message: String, val success: Boolean, val data : List<CategoryData>)
data class  CategoryData( val category_name : String)

//메뉴
data class  Menu(val message: String, val success: Boolean, val data: List<MenuData>)
data class  MenuData(val menu_name : String, val menu_price : Int, val menu_image : String )

//주문 내용 넣기
data class  Order(val message: String, val success: Boolean)
    //post
data class OrderItemPackage(val id: String, val order_list: List<OrderItem>, val total_price: Int )
data class OrderItem(val name: String, val count: Int, val sum_price: Int)

//주문 기록 가져오기
data class  OrderHistory(val message: String, val success: Boolean, val data: List<OrderHistoryData>)
data class  OrderHistoryData(val name : String, val count : Int, val sum_price : Int,val total_price : Int)


interface AccountApi{

    //아이디 중복체크
    @GET("/account/overlap")
    fun idDuplicateCheck(@Query("id") id: String) : Call<IdDuplicate>

    //회원가입 정보 주입
    @POST("/account")
    fun postSignUp(@Body userData : UserData) : Call<SignUp>

    //로그인 값 비교
    @GET("/account/login")
    fun getLogin(@Query("id") id: String, @Query("pw") pw: String): Call<Login>

}

interface CategoryApi{
    //카테고리 가져오기
    @GET("/category")
    fun getCategory(@Query("lang") lang : String) : Call<Category>

    @GET("/category")
    suspend fun getCategorySuspend(@Query("lang") lang : String) : Category

    //메뉴 가져오기
    @GET("/category/menu")
    fun getMenu(@Query("category_name") category_name: String, @Query("lang") lang: String ) : Call<Menu>

    @GET("/category/menu")
    suspend fun getMenuSuspend(@Query("category_name") category_name: String, @Query("lang") lang: String ) : Menu
}

interface OrderApi{
    //주문내용 넣기
    @POST("/order")
    fun postOrder(@Body orderItemPackage: OrderItemPackage) : Call<Order>
    suspend fun  postOrderSuspend(@Body orderItemPackage: OrderItemPackage) : Order

    //주문내용 가져오기
    @GET("/order")
    fun getOrderHistory(@Query("id") id: String) : Call<OrderHistory>
    suspend fun getOrderHistorySuspend(@Query("id") id: String) :OrderHistory
}