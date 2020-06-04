package org.dtransform.competencyapp.ui.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.dtransform.competencyapp.data.local.database.CompetencyDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class CompetencyDatabaseTest {
    protected lateinit var appDatabase: CompetencyDatabase
    private var appContext = InstrumentationRegistry.getInstrumentation().targetContext
    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            appContext,
            CompetencyDatabase::class.java)
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }
}