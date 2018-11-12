package com.elyeproj.wikisearchcount

import com.test.kotlinapp.Constants
import com.test.kotlinapp.repository.models.Employee
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.test.kotlinapp.repository.models.Emp
import retrofit2.http.*


interface APIService {

    @GET("employees")
    fun getEmployees(): Call<List<Employee>>

    @GET("employee/{id}")
    fun getEmployee(@Path("id") id:String): Call<Employee>

    @POST("create")
    fun createEmp(@Body emp: Emp): Call<Employee>

    @PUT("update/{id}")
    fun updateEmployee(@Path("id") id:String, @Body emp: Emp): Call<Employee>

    companion object {
        fun create(): APIService {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }

}