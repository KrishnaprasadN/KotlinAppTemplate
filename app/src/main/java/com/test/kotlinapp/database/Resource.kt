package com.test.kotlinapp.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Resource")
data class Resource(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "name") var name: String,
    var projectId: String
)