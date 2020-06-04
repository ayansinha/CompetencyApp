package org.dtransform.competencyapp.ui.associate.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.AssociateEntity



/**
 * @class{AssociateViewHolder} -> view-holder for associate
 */
class AssociateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var mName: TextView = itemView.findViewById(R.id.textViewName)
    var mBand: TextView = itemView.findViewById(R.id.textViewBand)
    var mID: TextView = itemView.findViewById(R.id.textViewID)
    var mDesignation: TextView = itemView.findViewById(R.id.textViewDesignation)
    var mCompetency: TextView = itemView.findViewById(R.id.textViewCompetency)
    var mCurrentProject: TextView = itemView.findViewById(R.id.textViewCurrentProject)

    fun bind(associateEntity: AssociateEntity , listener: AssociateAdapter.OnItemClickListener) {
        itemView.setOnClickListener {
            listener.onItemClick(associateEntity)
        }
    }
}
