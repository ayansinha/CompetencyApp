package org.dtransform.competencyapp.ui.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.dtransform.competencyapp.data.local.entity.ProjectEntity

/**
 * @class{ProjectAdapter} -> adapter for project
 */

class ProjectAdapter(
    private var projects: MutableList<ProjectEntity>,
    listener: OnItemClickListener
) : RecyclerView.Adapter<ProjectViewHolder>() {

    private var projectList: MutableList<ProjectEntity> = projects
    private var listenerProject: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(projectEntity: ProjectEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_projects, parent, false)
        )
    }

    override fun getItemCount() = projectList.size

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {

        val project: ProjectEntity = projectList[position]
        val name = project.projectName
        holder.mName.text = name
        holder.bind(project, listenerProject)

    }

    fun addProjects(list: MutableList<ProjectEntity>) {
        this.projectList = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        projectList.removeAt(position)
        notifyItemRemoved(position)
    }

}