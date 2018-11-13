package com.test.kotlinapp.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.R
import com.test.kotlinapp.repository.DataManager
import com.test.kotlinapp.repository.models.Emp
import com.test.kotlinapp.repository.models.Employee
import com.test.kotlinapp.utils.Logger
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    lateinit var mAPIService: APIService;
    var userListAdapter : UserListAdapter = UserListAdapter()

    companion object {
        fun newInstance() = MainFragment()
        private lateinit var viewModel: MainViewModel
    }



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


        viewModel.mEmployeeList.observe(this, Observer {
            Logger.d("api call ****  Total size is ${it?.size ?: "NULL"} ")
            listView.adapter = userListAdapter;
        })

        viewModel.getAllEmployees();


        mAPIService = APIService.create()

        // this is called only for test purpose, this should be moved to viewmodel class
        //getAllEmployees();
        //getEmployeeData("41")
        //createEmpployee(Emp("999", "Krishna", "100", "35"));




    }

   /* fun getAllEmployees() {
        val employees = DataManager(mAPIService).getEmployees()

        employees.observe(this, Observer(function = {
            Logger.d("Total size is ${it?.size ?: "NULL"} ")
        }))
    }*/

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


    class UserListAdapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.getContext())
            val view = inflater.inflate(R.layout.item, null)
            view.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT
            )
            return UserViewHolder(view)
        }


        override fun getItemCount(): Int {
            return viewModel.mEmployeeList.value!!.size
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, index: Int) {
            val employee = viewModel.mEmployeeList.value!!.get(index)
            val userViewHolder  = viewHolder as UserViewHolder
            userViewHolder.itemView.employeeName.setText(employee.employee_name)
            userViewHolder.itemView.setOnClickListener {


            }
        }


        class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }
    }



}
