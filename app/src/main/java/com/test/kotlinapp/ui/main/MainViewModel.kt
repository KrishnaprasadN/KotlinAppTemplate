package com.test.kotlinapp.ui.main

import android.arch.lifecycle.*
import com.test.kotlinapp.repository.models.Employee
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.repository.DataManager


class MainViewModel : ViewModel() {
     var employeeList: LiveData<List<Employee>> =  MutableLiveData()

    fun getAllEmployees(): LiveData<List<Employee>> {
        employeeList = DataManager(APIService.create()).getEmployees()
        return employeeList
    }
}
