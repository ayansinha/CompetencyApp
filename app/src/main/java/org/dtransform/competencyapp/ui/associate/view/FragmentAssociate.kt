package org.dtransform.competencyapp.ui.associate.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_associate.*
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.dtransform.competencyapp.ui.associate.adapter.AssociateAdapter
import org.dtransform.competencyapp.ui.associate.viewmodel.AssociateViewModel
import org.dtransform.competencyapp.util.SwipeToDeleteCallback


/**
 * @fragment{ActivityAddAssociate} -> view for associate
 */
class FragmentAssociate : Fragment(), AssociateAdapter.OnItemClickListener {

    private var displayList: MutableList<AssociateEntity> = ArrayList()
    private var associateSearchList: MutableList<AssociateEntity> = ArrayList()
    private lateinit var associateViewModel: AssociateViewModel
    private var recyclerViewAdapter: AssociateAdapter? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_associate, container, false)




        val toolbar: Toolbar = view.findViewById(R.id.toolBarAssociate)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.actionAddAssociate -> {
                    //Toast.makeText(activity, "add associate", Toast.LENGTH_SHORT).show()
                    requireContext().let {
                        val intent = Intent(it, ActivityAddAssociate::class.java)
                        intent.putExtra("addOrUpdate", "add")
                        startActivity(intent)
                    }
                }
                R.id.actionSearchAssociate -> {
                    val searchView = item.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {

                            if (newText!!.isNotEmpty()){

                                displayList.clear()
                                val search = newText.toLowerCase()
                                associateSearchList.forEach {
                                    if (it.associateName.contains(search)) {
                                        displayList.add(it)
                                    }
                                }

                            }else {
                                displayList.clear()
                                displayList.addAll(associateSearchList)
                                recyclerViewAssociate.adapter?.notifyDataSetChanged()

                            }
                            return true
                        }

                    })
                }
                else -> Log.e("else ", "clause")
            }
            true
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpUIViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpUIViewModel()
        /**
         * swipe to delete
         */
        val swipeHandler = object : SwipeToDeleteCallback(activity as AppCompatActivity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerViewAssociate.adapter as AssociateAdapter
                adapter.removeItem(viewHolder.adapterPosition)
                Log.e("POS ->" , "${viewHolder.adapterPosition}")
                deleteEmployeeById(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerViewAssociate)

        recyclerViewAssociate.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        recyclerViewAdapter = AssociateAdapter(arrayListOf(), this)
        recyclerViewAssociate.adapter = recyclerViewAdapter
    }

    fun deleteEmployeeById(index: Int) {
        associateViewModel.delete(index.toLong())
    }


    private fun setUpUIViewModel() {

        associateViewModel = ViewModelProvider(activity as AppCompatActivity).get(AssociateViewModel::class.java)

        associateViewModel.allAssociates.observe(activity as AppCompatActivity, Observer {
            if (it.isEmpty()) {
                Log.e("TAG EMPTY", "ITEMS: $it")

            } else {
                displayList.addAll(it)
                associateSearchList = it
                recyclerViewAdapter!!.addAssociates(it)
                recyclerViewAdapter!!.notifyDataSetChanged()
            }
            Log.e("TAG", "ITEMS: $it")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_associate, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionAddAssociate -> {
                //Toast.makeText(activity , "add on click" , Toast.LENGTH_SHORT).show()
                Log.e("add", "add Associate")
                true
            }
            R.id.actionSearchAssociate -> {
                Log.e("search", "search Associate")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(associateEntity: AssociateEntity) {
        Log.e("name", " " + associateEntity.associateName)
        Log.e("id", " " + associateEntity.associateId)
        Log.e("band", " " + associateEntity.associateBand)
        Log.e("designation", " " + associateEntity.associateDesignation)
        Log.e("competency", " " + associateEntity.associateCompetency)
        Log.e("cp", " " + associateEntity.associateCurrentProject)

        val intent = Intent(context, ActivityAddAssociate::class.java)

        intent.putExtra("addOrUpdate", "update")
        intent.putExtra("id", associateEntity.associateId)
        intent.putExtra("name", associateEntity.associateName)
        intent.putExtra("band", associateEntity.associateBand)
        intent.putExtra("designation", associateEntity.associateDesignation)
        intent.putExtra("competency", associateEntity.associateCompetency)
        intent.putExtra("cp", associateEntity.associateCurrentProject)
        startActivity(intent)
    }

}
