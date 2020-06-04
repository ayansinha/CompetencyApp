package org.dtransform.competencyapp.ui.associate


import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.ui.associate.view.FragmentAssociate
import org.dtransform.competencyapp.ui.dashboard.DashboardActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FragmentAssociteTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(DashboardActivity::class.java)

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

    /**
     * check if {associate fragment} is Displayed
     */
    @Test
    fun checkAssociateFragmentIsDisplayed() {
        val fragmentAssociate = FragmentAssociate()
        activityRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragmentAssociate).commit()
    }



    @Test
    fun fragmentAssociteTest() {
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.actionAddAssociate), withContentDescription("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.toolBarAssociate),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.inputAssociateID),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutEmployeeID),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("474862"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.inputAssociateName),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutEmployeeName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("Ayan Sinha"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.inputAssociateBand),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutEmployeeBand),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("U3"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.inputAssociateDesignation),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutEmployeeDesignation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("Sr. Engineer"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.inputAssociateDesignation), withText("Sr. Engineer"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutEmployeeDesignation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(click())

        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.radioButtonAndroid), withText("Android"),
                childAtPosition(
                    allOf(
                        withId(R.id.radioGroup),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            5
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatRadioButton.perform(click())

        val appCompatButton = onView(
            allOf(
                withId(R.id.buttonSubmitAssociate), withText("SUBMIT"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
