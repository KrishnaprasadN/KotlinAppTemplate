package com.test.kotlinapp.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.kotlinapp.R
import com.test.kotlinapp.ui.main.ui.employee.EmployeeFragment

class EmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employee_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EmployeeFragment.newInstance())
                .commitNow()
        }
    }
}
