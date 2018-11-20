package com.test.kotlinapp.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.R
import com.test.kotlinapp.utils.Logger
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    lateinit var mAPIService: APIService;
    //var userListAdapter : UserListAdapter = UserListAdapter()

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

        listView.layoutManager = LinearLayoutManager(this@MainFragment.context)


        Logger.d("Fragment - > getAllEmployees");
        viewModel.getAllEmployees().observe(this, Observer {
            Logger.d("api call ****  Total size is ${it?.size ?: "NULL"} ")
            listView.adapter = UserListAdapter();
        })

    }

    class UserListAdapter :  RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.getContext())
            val view = inflater.inflate(R.layout.item, null)

            return UserViewHolder(view)
        }


        override fun getItemCount(): Int {
            return viewModel.employeeList.value!!.size
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, index: Int) {
            val employee = viewModel.employeeList.value!!.get(index)
            val userViewHolder  = viewHolder as UserViewHolder
            userViewHolder.itemView.employeeName.setText(employee.employee_name)
            userViewHolder.itemView.setOnClickListener {


            }
        }


        class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }
    }



}
