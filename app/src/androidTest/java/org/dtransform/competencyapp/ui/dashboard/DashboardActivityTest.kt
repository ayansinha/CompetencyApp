package org.dtransform.competencyapp.ui.dashboard

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardActivityTest {

    @get: Rule
    val activityRule: ActivityTestRule<DashboardActivity> =
        ActivityTestRule(DashboardActivity::class.java, false, false)
    private val intent = Intent()

    @Before
    fun setUp() {
        activityRule.launchActivity(intent)
    }

    /**
     * check if app launch successfully
     */
    @Test
    fun appLaunchSuccessfully() {
        ActivityScenario.launch(DashboardActivity::class.java)
    }


}