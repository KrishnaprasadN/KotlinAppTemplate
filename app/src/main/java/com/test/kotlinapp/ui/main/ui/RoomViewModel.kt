package com.test.kotlinapp.ui.main.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.elyeproj.wikisearchcount.APIService
import com.test.kotlinapp.database.AppDatabase
import com.test.kotlinapp.repository.DataManager

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

    
}
