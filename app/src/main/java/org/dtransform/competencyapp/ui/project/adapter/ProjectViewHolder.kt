package org.dtransform.competencyapp.ui.project.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.ProjectEntity


/**
 * @class{ProjectViewHolder} -> view-holder for project
 */
class ProjectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var mName: TextView = itemView.findViewById(R.id.textViewProjectName)

    fun bind(projectEntity: ProjectEntity, listener: ProjectAdapter.OnItemClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(projectEntity)
        }
    }
}
