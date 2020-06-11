package org.dtransform.competencyapp.ui.associate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.AssociateEntity

/**
 * @class{AssociateAdapter} -> adapter for associate
 */

class AssociateAdapter(private var associates: MutableList<AssociateEntity>, listener: OnItemClickListener): RecyclerView.Adapter<AssociateViewHolder>() {

    private var associateList: MutableList<AssociateEntity> = associates
    private var listenerAssociate: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(associateEntity: AssociateEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssociateViewHolder {

        return AssociateViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_associate , parent , false))
    }

    override fun getItemCount() = associateList.size


    override fun onBindViewHolder(holder: AssociateViewHolder, position: Int) {
        val associate:AssociateEntity = associateList[position]

        val name = associate.associateName
        val id = associate.associateId
        val band = "(${associate.associateBand})"
        val competency = associate.associateCompetency
        val designation = associate.associateDesignation
        val currentProject = associate.associateCurrentProject

        holder.mName.text = name
        holder.mBand.text = band
        holder.mID.text = id
        holder.mCompetency.text = competency
        holder.mDesignation.text = designation
        holder.mCurrentProject.text = currentProject

        holder.bind(associate , listenerAssociate)

    }

    fun addAssociates(list: MutableList<AssociateEntity>) {
        this.associateList = list
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        associateList.removeAt(position)
        notifyItemRemoved(position)
    }

}