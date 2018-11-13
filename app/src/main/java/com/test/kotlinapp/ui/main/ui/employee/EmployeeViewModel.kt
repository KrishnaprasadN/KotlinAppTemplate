package com.test.kotlinapp.ui.main.ui.employee

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.repository.DataManager
import com.test.kotlinapp.repository.models.Employee
import java.util.logging.Logger

class EmployeeViewModel : ViewModel() {
    lateinit var employee: LiveData<Employee>


    private var mDataManager: DataManager = DataManager(APIService.create())

    fun getEmpDetails(id:String)  {

        val employee1 = mDataManager.getEmployee(id)
        com.test.kotlinapp.utils.Logger.d("Emp name is ${employee1.value}");
    }
}
