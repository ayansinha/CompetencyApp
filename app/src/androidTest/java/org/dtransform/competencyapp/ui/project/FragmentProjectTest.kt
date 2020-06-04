package org.dtransform.competencyapp.ui.project

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.ui.dashboard.DashboardActivity
import org.dtransform.competencyapp.ui.project.view.FragmentProject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FragmentProjectTest {

    private val intent = Intent()

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(DashboardActivity::class.java)

    @get: Rule
    val activityRule: ActivityTestRule<DashboardActivity> =
        ActivityTestRule(DashboardActivity::class.java, false, false)

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

    /**
     * check if {project fragment} is Displayed
     */
    @Test
    fun checkProjectFragmentIsDisplayed() {
        val fragmentProject = FragmentProject()
        activityRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragmentProject).commit()
    }


}