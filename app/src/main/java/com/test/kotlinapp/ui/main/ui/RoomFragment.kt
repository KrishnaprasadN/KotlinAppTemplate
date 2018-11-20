package com.test.kotlinapp.ui.main.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.test.kotlinapp.R
import com.test.kotlinapp.database.Project
import com.test.kotlinapp.utils.Logger
import kotlinx.android.synthetic.main.room_fragment.*
import kotlin.random.Random

class RoomFragment : Fragment() {

    companion object {
        fun newInstance() = RoomFragment()
    }

    private lateinit var mViewModel: RoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)

        create_db.setOnClickListener {
            mViewModel.createDB()
        }

        create_project.setOnClickListener {
            var number = Random.nextInt(0, 100)
            mViewModel.createNewProject(number.toLong(), "Project $number")
        }

        create_resource.setOnClickListener {
            var number = Random.nextInt(0, 100)
            mViewModel.createNewResource(number.toLong(), "Resource $number")
        }

        show_project_count.setOnClickListener {
            var count = mViewModel.getProjectCount().observe(this, Observer {
                result.text = "Total Project Count is $it"
            })
        }

        show_resource_count.setOnClickListener {
            var count = mViewModel.getResourceCount().observe(this, Observer {
                result.text = "Total Resource Count is $it"
            })
        }

        show_project.setOnClickListener {
            mViewModel.getAllProjects().observe(this, Observer {
                Logger.d("Projects are ${it}");
                result.text = it.toString()
            })
        }

        show_resources.setOnClickListener {
            mViewModel.getAllResources().observe(this, Observer {
                Logger.d("Resources are ${it}");
                result.text = it.toString()
            })
        }
    }

}
