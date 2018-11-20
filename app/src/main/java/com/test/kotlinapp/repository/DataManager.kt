package com.test.kotlinapp.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.elyeproj.wikisearchcount.APIService
import com.google.samples.apps.sunflower.utilities.runOnIoThread
import com.test.kotlinapp.database.AppDatabase
import com.test.kotlinapp.database.Project
import com.test.kotlinapp.database.Resource
import com.test.kotlinapp.database.ResourceDao
import com.test.kotlinapp.repository.models.Employee
import com.test.kotlinapp.repository.models.Emp
import com.test.kotlinapp.utils.Logger
import retrofit2.Callback

class DataManager {
    private val mApiService: APIService
    private lateinit var mAppDatabase: AppDatabase

    constructor(apiService: APIService) {
        mApiService = apiService;
    }

    constructor(
        apiService: APIService,
        appDatabase: AppDatabase
    ) {
        mApiService = apiService;
        mAppDatabase = appDatabase
    }

    fun getEmployees(): LiveData<List<Employee>> {
        Logger.d("DataManager - > getAllEmployees");
        var data: MutableLiveData<List<Employee>> = MutableLiveData()

        mApiService.getEmployees().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: retrofit2.Call<List<Employee>>,
                response: retrofit2.Response<List<Employee>>
            ) {
                Logger.d("DataManager - > getAllEmployees - > Response");
                data.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<List<Employee>>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }

    fun getEmployee(id: Number): LiveData<Employee> {
        var data = MutableLiveData<Employee>()

        mApiService.getEmployee(id).enqueue(object : Callback<Employee> {
            override fun onResponse(
                call: retrofit2.Call<Employee>,
                response: retrofit2.Response<Employee>
            ) {
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
                response: retrofit2.Response<Employee>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<Employee>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }

    fun updateEmployee(id: String, emp: Emp): LiveData<Employee> {
        var data = MutableLiveData<Employee>()

        mApiService.updateEmployee(id, emp).enqueue(object : Callback<Employee> {
            override fun onResponse(
                call: retrofit2.Call<Employee>,
                response: retrofit2.Response<Employee>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<Employee>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }


    ////////////////////////////////////////////////////////////////////////////
    // DB Related Methods //
    ////////////////////////////////////////////////////////////////////////////

    fun createDB(): AppDatabase {
        return mAppDatabase
    }

    fun createNewProject(id: Long, name: String) {
        runOnIoThread {
            mAppDatabase.getProjectDao().inset(Project(id, name, ""))
        }
    }

    fun createNewResource(id: Long, name: String) {
        runOnIoThread {
            mAppDatabase.getResourceDao().inset(Resource(id.toString(), name, ""))
        }
    }

    fun getProjectCount(): LiveData<Int> {
        return mAppDatabase.getProjectDao().getCount()
    }

    fun getResourceCount(): LiveData<Int> {
        return mAppDatabase.getResourceDao().getCount()
    }
}