package com.test.kotlinapp.database

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class ProjectResources {

    @Embedded
    var project: Project? = null

    @Relation(parentColumn = "id", entityColumn = "projectId", entity = Resource::class)
    var resources: List<Resource>? = null

}