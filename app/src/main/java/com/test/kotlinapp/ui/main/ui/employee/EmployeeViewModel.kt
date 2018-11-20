package com.test.kotlinapp.ui.main.ui.employee

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.repository.DataManager
import com.test.kotlinapp.repository.models.Employee

class EmployeeViewModel : ViewModel() {

    var employee: LiveData<Employee> = MutableLiveData()

    private var mDataManager: DataManager = DataManager(APIService.create())

    fun getEmpDetails(id: Number): LiveData<Employee> {
        employee = mDataManager.getEmployee(id);

        return employee
    }

}
