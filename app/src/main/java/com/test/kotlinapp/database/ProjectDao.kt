package com.test.kotlinapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface ProjectDao {

    @Query("Select * from Project")
    fun getAllProjects(): List<Project>

    @Insert(onConflict = REPLACE)
    fun inset(project: Project)

    @Query("Delete from Project")
    fun deleteAll()

    @Delete
    fun delete(project: Project)

    @Query("Select COUNT(*) from Project")
    fun getCount(): LiveData<Int>
}