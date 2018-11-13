package com.test.kotlinapp.ui.main.ui.employee

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.kotlinapp.R

class EmployeeFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeeFragment()
    }

    private lateinit var mViewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.employee_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)

        mViewModel.employee.observe(this, Observer {

        })

        mViewModel.getEmpDetails("56")
    }

}
