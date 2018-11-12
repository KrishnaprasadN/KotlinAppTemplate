package com.test.kotlinapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.net.LinkAddress
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.repository.models.Employee
import okhttp3.Call
import retrofit2.Callback
import java.io.IOException

class DataManager {
    private val mApiService: APIService;

    constructor(apiService: APIService) {
        mApiService = apiService;
    }

    fun getEmployees(): LiveData<List<Employee>> {
        var data = MutableLiveData<List<Employee>>()

        mApiService.getEmployees().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: retrofit2.Call<List<Employee>>,
                response: retrofit2.Response<List<Employee>>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<List<Employee>>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }

    fun getEmployee(id:String): LiveData<Employee> {
        var data = MutableLiveData<Employee>()

        mApiService.getEmployee(id).enqueue(object : Callback<Employee> {
            override fun onResponse(
                call: retrofit2.Call<Employee>,
                response: retrofit2.Response<Employee>)
            {
                data.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<Employee>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }
}