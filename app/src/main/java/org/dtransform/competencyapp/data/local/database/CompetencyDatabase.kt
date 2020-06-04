package org.dtransform.competencyapp.data.local.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.dtransform.competencyapp.data.local.dao.AssociateDAO
import org.dtransform.competencyapp.data.local.dao.ProjectDAO
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.dtransform.competencyapp.data.local.entity.ProjectEntity

/**
 * @class{CompetencyDatabase} -> database
 */

@Database(entities = [AssociateEntity::class , ProjectEntity::class] , version = 1 , exportSchema = false)
abstract class CompetencyDatabase: RoomDatabase() {

    abstract fun associateDao(): AssociateDAO
    abstract fun projectDao(): ProjectDAO

    companion object {
        @Volatile
        private var instance: CompetencyDatabase? = null

        fun getInstance(context: Context): CompetencyDatabase? {
            if (instance == null) {
                synchronized(CompetencyDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CompetencyDatabase::class.java, "competency_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }


}