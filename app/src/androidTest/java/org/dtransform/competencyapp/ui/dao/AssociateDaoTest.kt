package org.dtransform.competencyapp.ui.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class AssociateDaoTest : CompetencyDatabaseTest() {

    @Test
    fun insertAssociateEntity() = runBlocking {
        val associateEntity = AssociateEntity(
            associateId = "474862",
            associateBand = "U3",
            associateDesignation = "Sr. Software Engineer",
            associateName = "Ayan Sinha",
            associateCompetency = "Android",
            associateCurrentProject = "ATT"
        )

        val associateId = appDatabase.associateDao().createAssociate(associateEntity)
        Assert.assertEquals(associateId, 1)
    }

    @Test
    fun fetchAssociateIsNullOrEmpty(): Unit = runBlocking {
        withContext(Dispatchers.Main) {
            val associateEntity = AssociateEntity(
                associateId = "474862",
                associateBand = "U3",
                associateDesignation = "Sr. Software Engineer",
                associateName = "Ayan Sinha",
                associateCompetency = "Android",
                associateCurrentProject = "ATT"
            )

            val associateId = appDatabase.associateDao().createAssociate(associateEntity)

            val associateList = appDatabase.associateDao().fetchAllAssociates()
            Assert.assertEquals(associateList.value?.size, null)
        }
    }
}