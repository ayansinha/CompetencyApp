package org.dtransform.competencyapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import org.dtransform.competencyapp.data.local.dao.AssociateDAO
import org.dtransform.competencyapp.data.local.dao.ProjectDAO
import org.dtransform.competencyapp.data.local.database.CompetencyDatabase
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.dtransform.competencyapp.data.local.entity.ProjectEntity

/**
 * @class{ProjectRepository} -> repository for project
 */

class ProjectRepository(application: Application) {

    private val projectDAO: ProjectDAO
    private var projectList: LiveData<MutableList<ProjectEntity>>

    init {
        val db = CompetencyDatabase.getInstance(application)
        projectDAO = db?.projectDao()!!
        projectList = projectDAO.fetchAllProjects()
    }

    fun fetchListOfProjects(): LiveData<MutableList<ProjectEntity>> {
        return projectList
    }

    suspend fun inertListOfProjects(projectList: MutableList<ProjectEntity>) {
        projectDAO.insertListOfProjects(projectList)
    }

    suspend fun insertProject(projectEntity: ProjectEntity) {
        projectDAO.createProject(projectEntity)
    }

}