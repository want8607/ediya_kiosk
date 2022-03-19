package com.example.stage.ServerConnection

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//무조건 리턴값 가져야하고 객체여야함
object RetrofitClient{
    fun initRetrofit(): Retrofit{

        val url = "http://3.35.214.92:3000" // 서버주소
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
//받아온 데이터를 저장할 객체
data class  RegistData(val message: String, val success: Boolean)
data class  LoginData(val message: String, val success: Boolean)
data class  CategoryData(val message: String, val success: Boolean, val data : List<String>)
data class  MenuData(val message: String, val success: Boolean, val data: List<category_name<String>>)
//API로 요청을 보내는 함수
interface LoginApi {
    @GET("/account/login")
    fun getLogin(@Query("id") id: String, @Query("pw") pw: String): Call<LoginData> //이게 enqueue로 들어감
}