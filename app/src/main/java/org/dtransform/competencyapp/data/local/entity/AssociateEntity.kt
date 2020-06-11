package org.dtransform.competencyapp.data.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @class{AssociateEntity} -> entity for associate
 */

@Entity(tableName = "associate")
data class AssociateEntity(
    @ColumnInfo(name = "associateID")
    var associateId: String,

    @ColumnInfo(name = "associateNAME")
    var associateName: String,

    @ColumnInfo(name = "associateBAND")
    var associateBand: String,

    @ColumnInfo(name = "associateDESIGNATION")
    var associateDesignation: String,

    @ColumnInfo(name = "associateCOMPETENCY")
    var associateCompetency: String,

    @ColumnInfo(name = "associateCP")
    var associateCurrentProject: String
    ) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}