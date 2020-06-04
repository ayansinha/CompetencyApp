package org.dtransform.competencyapp.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.app.CompetencyApplication
import org.dtransform.competencyapp.data.local.entity.ProjectEntity
import org.dtransform.competencyapp.ui.associate.view.FragmentAssociate
import org.dtransform.competencyapp.ui.project.view.FragmentProject
import org.dtransform.competencyapp.ui.project.viewmodel.ProjectViewModel


/**
 * @activity{DashboardActivity} -> view for dashboard, which binds both(associate & project) views
 */
class DashboardActivity : AppCompatActivity() {

    var list: MutableList<ProjectEntity> = ArrayList()
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var app: CompetencyApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initUI(FragmentAssociate())
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_associate -> {
                    initUI(FragmentAssociate())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_project -> {
                    initUI(FragmentProject())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

    }

    private fun initUI(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }
    }
}
