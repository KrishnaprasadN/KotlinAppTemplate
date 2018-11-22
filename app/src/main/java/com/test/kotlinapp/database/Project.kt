package com.test.kotlinapp.database

import android.arch.persistence.room.*


@Entity(tableName = "Project")
data class Project(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "name") var name: String,
    @Embedded var manager: Manager
)