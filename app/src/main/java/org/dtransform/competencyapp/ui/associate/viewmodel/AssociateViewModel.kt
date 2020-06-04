package org.dtransform.competencyapp.ui.associate.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.dtransform.competencyapp.data.repository.AssociateRepository

/**
 * @class{AssociateViewModel} -> view-model for associate
 */

class AssociateViewModel(application: Application): AndroidViewModel(application) {

    private val repository: AssociateRepository = AssociateRepository(application)
    val allAssociates: LiveData<MutableList<AssociateEntity>>

    init {
        allAssociates = repository.fetchListOfAssociates()
    }

    /**
     * insert single associate
     */
    fun insert(associateEntity: AssociateEntity) = viewModelScope.launch {
        repository.insertAssociate(associateEntity)
    }

    /**
     * update associate
     */
    fun update(associateEntity: AssociateEntity) = viewModelScope.launch {
        repository.updateAssociate(associateEntity)
    }

    /**
     * delete associate
     */
    fun delete(id: Long) = viewModelScope.launch {
        repository.deleteAssociate(id)
    }
}