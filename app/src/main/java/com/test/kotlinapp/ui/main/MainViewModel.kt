package com.test.kotlinapp.ui.main

import android.arch.lifecycle.*
import com.test.kotlinapp.repository.models.Employee
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.repository.DataManager


class MainViewModel : ViewModel() {

    fun getAllEmployees() {

       DataManager(APIService.create()).getEmployees(mEmployeeList)

    }

    // TODO: Implement the ViewModel
     var mEmployeeList: MutableLiveData<List<Employee>> =  MutableLiveData()

}
