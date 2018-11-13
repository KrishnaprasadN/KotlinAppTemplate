package com.test.kotlinapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.repository.models.Employee
import com.test.kotlinapp.repository.models.Emp
import retrofit2.Callback

class DataManager {
    private val mApiService: APIService;

    constructor(apiService: APIService) {
        mApiService = apiService;
    }

    fun getEmployees(mEmployeeList: MutableLiveData<List<Employee>>) {
        mApiService.getEmployees().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: retrofit2.Call<List<Employee>>,
                response: retrofit2.Response<List<Employee>>
            ) {
                mEmployeeList.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<List<Employee>>, t: Throwable) {
                mEmployeeList.value = null
            }
        })
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