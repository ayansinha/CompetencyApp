package org.dtransform.competencyapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import org.dtransform.competencyapp.data.local.dao.AssociateDAO
import org.dtransform.competencyapp.data.local.database.CompetencyDatabase
import org.dtransform.competencyapp.data.local.entity.AssociateEntity

/**
 * @class{AssociateRepository} -> repository for associate
 */

class AssociateRepository(application: Application) {


    private val associateDAO: AssociateDAO
    private var associateList: LiveData<MutableList<AssociateEntity>>

    init {
        val db = CompetencyDatabase.getInstance(application)
        associateDAO = db?.associateDao()!!
        associateList = associateDAO.fetchAllAssociates()
    }

    init {
        associateList = associateDAO.fetchAllAssociates()
    }

    /**
     * fetching list of associate
     */
    fun fetchListOfAssociates(): LiveData<MutableList<AssociateEntity>> {
        return associateList
    }


    /**
     * inserting single associate
     */
    suspend fun insertAssociate(associateEntity: AssociateEntity) {
        associateDAO.createAssociate(associateEntity)
    }

    /**
     * update associate
     */
    suspend fun updateAssociate(associateEntity: AssociateEntity) {
        associateDAO.updateAssociate(associateEntity)
    }

    /**
     * delete single associate
     */
    suspend fun deleteAssociate(id:Long) {
        associateDAO.deleteAssociateById(id)
    }

}