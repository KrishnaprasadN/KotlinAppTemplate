package com.test.kotlinapp.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


data class Manager(
    @ColumnInfo(name = "manager_id") var managerId: Int,
    @ColumnInfo(name = "manager_name")var name: String,
    @ColumnInfo(name = "manager_age") var age: Int
)