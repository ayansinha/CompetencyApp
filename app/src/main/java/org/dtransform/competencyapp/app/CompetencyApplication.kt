package org.dtransform.competencyapp.app

import android.annotation.SuppressLint
import android.app.Application

/**
 * @application{CompetencyApplication}
 */

@SuppressLint("Registered")
class CompetencyApplication: Application() {

    var isInserted = false


    companion object {
        @get:Synchronized
        lateinit var init: CompetencyApplication
        private set
    }

    override fun onCreate() {
        super.onCreate()
        init = this
    }

    fun setEntity(entity: Boolean) {
        isInserted = entity
    }

    fun getEntity():Boolean {
        return isInserted
    }
}