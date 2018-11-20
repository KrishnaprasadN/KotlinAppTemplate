package com.test.kotlinapp.ui.main.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.elyeproj.wikisearchcount.APIService
import com.google.samples.apps.sunflower.utilities.runOnIoThread
import com.test.kotlinapp.database.AppDatabase
import com.test.kotlinapp.database.Project
import com.test.kotlinapp.database.Resource
import com.test.kotlinapp.repository.DataManager
import com.test.kotlinapp.utils.Logger

class RoomViewModel(application: Application) : AndroidViewModel(application) {

    private var mDataManager: DataManager = DataManager(APIService.create(), AppDatabase.getInstance(application)!!)

    fun createDB(){
        mDataManager.createDB()
    }

    fun createNewProject(id:Long, name:String) {
        mDataManager.createNewProject(id, name)
    }

    fun createNewResource(id: Long, name: String) {
        mDataManager.createNewResource(id, name)
    }

    fun getProjectCount(): LiveData<Int> {
        return mDataManager.getProjectCount()
    }

    fun getResourceCount(): LiveData<Int> {
        return mDataManager.getResourceCount()
    }

    fun getAllProjects(): LiveData<List<Project>> {
        Logger.logThreadDetails("RoomViewModel")
        return mDataManager.getAllProjects()
    }

    fun getAllResources(): LiveData<List<Resource>> {
        return mDataManager.getAllResources()
    }
}
