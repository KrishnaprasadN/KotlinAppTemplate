package com.test.kotlinapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface ResourceDao {

    @Query("Select * from Resource")
    fun getAllResources(): LiveData<List<Resource>>

    @Insert(onConflict = REPLACE)
    fun insert(resource: Resource)

    @Query("Delete from Resource")
    fun deleteAll()

    @Delete
    fun delete(resource: Resource)

    @Query("Select COUNT(*) from Resource")
    fun getCount(): LiveData<Int>
}