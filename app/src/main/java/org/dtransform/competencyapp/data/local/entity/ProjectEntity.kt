package org.dtransform.competencyapp.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @class{ProjectEntity} -> entity for project
 */

@Entity(tableName = "project")
data class ProjectEntity(

    @ColumnInfo(name = "projectName")
    var projectName: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}