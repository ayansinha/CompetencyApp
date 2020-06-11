package org.dtransform.competencyapp.ui.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.dtransform.competencyapp.data.local.entity.ProjectEntity
import org.dtransform.competencyapp.util.Constants.Companion.ATT_FIRSTNET
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class ProjectDaoTest : CompetencyDatabaseTest() {

    @Test
    fun insertProjectEntity() = runBlocking {
        val projectEntity = ProjectEntity(projectName = ATT_FIRSTNET)
        val projectId = appDatabase.projectDao().createProject(projectEntity)
        assertEquals(projectId, 1)
    }

    @Test
    fun fetchProjectsIsNullOrEmpty(): Unit = runBlocking {
        withContext(Main) {
            val projectEntity = ProjectEntity(projectName = ATT_FIRSTNET)
            val projectId = appDatabase.projectDao().createProject(projectEntity)
            val projectList = appDatabase.projectDao().fetchAllProjects()
            assertEquals(projectList.value?.size,null)
        }
    }


}