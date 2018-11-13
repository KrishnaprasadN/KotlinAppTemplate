package com.test.kotlinapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.repository.models.Employee
import com.test.kotlinapp.repository.models.Emp
import com.test.kotlinapp.utils.Logger
import retrofit2.Callback

class DataManager {
    private val mApiService: APIService;

    constructor(apiService: APIService) {
        mApiService = apiService;
    }

    fun getEmployees(): LiveData<List<Employee>> {
        var data: MutableLiveData<List<Employee>> = MutableLiveData()

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

    fun getEmployee(id:Number):LiveData<Employee> {
        var data = MutableLiveData<Employee>()

        mApiService.getEmployee(id).enqueue(object : Callback<Employee> {
            override fun onResponse(
                call: retrofit2.Call<Employee>,
                response: retrofit2.Response<Employee>)
            {
                //Logger.d("onresponse ${response.body().toString()}")
                data.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<Employee>, t: Throwable) {
                Logger.d("onFailure...")
                data.value = null
            }
        })

        return data
    }

    fun createEmployee(emp: Emp): LiveData<Employee> {
        var data = MutableLiveData<Employee>()

        mApiService.createEmp(emp).enqueue(object : Callback<Employee> {
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

    fun updateEmployee(id:String, emp: Emp): LiveData<Employee> {
        var data = MutableLiveData<Employee>()

        mApiService.updateEmployee(id, emp).enqueue(object : Callback<Employee> {
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