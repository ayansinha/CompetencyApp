package org.dtransform.competencyapp.ui.project.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_project.*
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.ProjectEntity
import org.dtransform.competencyapp.ui.project.viewmodel.ProjectViewModel

/**
 * @activity{ActivityAddProject} -> view for project
 */
class ActivityAddProject: AppCompatActivity() {

    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var projectEntity: ProjectEntity
    private var isAddOrUpdate: String? = null
    private var projectName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_project)

        isAddOrUpdate = intent.extras?.getString("addOrUpdate")
        projectName = intent.extras?.getString("projectName")
        Log.e("isAddOrUpdate : " , "$isAddOrUpdate")
        Log.e("projectName : " , "$projectName")

        projectViewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        progressBarProject.visibility = View.GONE
        initUI()
    }

    private fun initUI() {
        if (isAddOrUpdate.equals("update"))
            inputCurrentProject.setText(projectName)

        val projectName = inputCurrentProject.text
        inputCurrentProject.text = projectName
        buttonSubmitProject.setOnClickListener {
            progressBarProject.visibility = View.VISIBLE
            Log.e("project name" , projectName.toString())
            projectEntity = ProjectEntity(inputCurrentProject.text.toString())

            projectViewModel.insert(projectEntity)
            progressBarProject.visibility = View.GONE
        }
    }
}