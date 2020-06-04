package org.dtransform.competencyapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.dtransform.competencyapp.data.local.entity.AssociateEntity

/**
 * @DAO{AssociateDAO}
 */

@Dao
interface AssociateDAO {

    @Query("SELECT * FROM associate")
    fun fetchAllAssociates(): LiveData<MutableList<AssociateEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAssociate(associateEntity: AssociateEntity)

    @Query("DELETE FROM associate WHERE associateId = :id")
    suspend fun deleteAssociateById(id: Long)

    @Update
    suspend fun updateAssociate(associateEntity: AssociateEntity)
}