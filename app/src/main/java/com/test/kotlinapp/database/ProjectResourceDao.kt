package com.test.kotlinapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface ProjectResourceDao {

    @Query("SELECT * from Project")
    fun getProjectResources(): LiveData<List<ProjectResources>>
}