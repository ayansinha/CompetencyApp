package org.dtransform.competencyapp.ui.project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.dtransform.competencyapp.data.local.entity.ProjectEntity
import org.dtransform.competencyapp.data.repository.ProjectRepository

/**
 * @class{ProjectViewModel} -> view-model for project
 */

class ProjectViewModel(application: Application): AndroidViewModel(application) {


    private val repository: ProjectRepository = ProjectRepository(application)
    val allProjects: LiveData<MutableList<ProjectEntity>>

    init {
        allProjects = repository.fetchListOfProjects()
    }

    /**
     * insert project
     */
    fun insert(projectEntity: ProjectEntity) = viewModelScope.launch {
        repository.insertProject(projectEntity)
    }

    /**
     * insert list of project
     */
    fun insertListOfProjects(projectList: MutableList<ProjectEntity>) = viewModelScope.launch {
        repository.inertListOfProjects(projectList)
    }
}