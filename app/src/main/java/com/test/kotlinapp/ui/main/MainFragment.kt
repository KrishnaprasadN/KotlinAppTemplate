package com.test.kotlinapp.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.R
import com.test.kotlinapp.repository.DataManager
import com.test.kotlinapp.repository.models.Emp
import com.test.kotlinapp.utils.Logger

class MainFragment : Fragment() {

    lateinit var mAPIService: APIService;

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        mAPIService = APIService.create()

        // this is called only for test purpose, this should be moved to viewmodel class
        //getAllEmployees();
        //getEmployeeData("41")
        //createEmpployee(Emp("999", "Krishna", "100", "35"));
    }

    fun getAllEmployees() {
        val employees = DataManager(mAPIService).getEmployees()

        employees.observe(this, Observer(function = {
            Logger.d("Total size is ${it?.size ?: "NULL"} ")
        }))
    }

    fun getEmployeeData(id:String) {
        val employee = DataManager(mAPIService).getEmployee(id)

        employee.observe(this, Observer {
            Logger.d("Id is $id, Employee is ${it.toString()} ")
        })
    }

    fun createEmpployee(emp:Emp) {
        val employee = DataManager(mAPIService).createEmployee(emp)

        employee.observe(this, Observer {
            Logger.d("EMP is created, Response Employee is ${it.toString()} ")
        })
    }
}
