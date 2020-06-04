package org.dtransform.competencyapp.ui.project.view

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_associate.*
import kotlinx.android.synthetic.main.fragment_project.*
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.dtransform.competencyapp.data.local.entity.ProjectEntity
import org.dtransform.competencyapp.ui.associate.adapter.AssociateAdapter
import org.dtransform.competencyapp.ui.associate.view.ActivityAddAssociate
import org.dtransform.competencyapp.ui.project.adapter.ProjectAdapter
import org.dtransform.competencyapp.ui.project.viewmodel.ProjectViewModel
import org.dtransform.competencyapp.util.SwipeToDeleteCallback

/**
 * @fragment{FragmentProject} -> view for project
 */
class FragmentProject : Fragment(), ProjectAdapter.OnItemClickListener {

    private var displayList: MutableList<ProjectEntity> = ArrayList()
    private var projectSearchList: MutableList<ProjectEntity> = ArrayList()
    private var it: MutableList<ProjectEntity> = ArrayList()
    private lateinit var projectViewModel: ProjectViewModel

    private var recyclerViewAdapter: ProjectAdapter? = null
    private lateinit var projectEntity: ProjectEntity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_project, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.toolBarProject)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.actionAddProject -> {

                    activity?.let {
                        val intent = Intent(it, ActivityAddProject::class.java)
                        intent.putExtra("addOrUpdate", "add")
                        startActivity(intent)
                    }
                }
                R.id.actionSearchProject -> {
                    val searchView = item.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {

                            if (newText!!.isNotEmpty()) {

                                displayList.clear()
                                val search = newText.toLowerCase()
                                projectSearchList.forEach {
                                    if (it.projectName.contains(search)) {
                                        displayList.add(it)
                                    }
                                }

                            } else {
                                displayList.clear()
                                displayList.addAll(projectSearchList)
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

        /**
         * swipe to delete
         */
        val swipeHandler = object : SwipeToDeleteCallback(activity as AppCompatActivity) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerViewAssociate.adapter as AssociateAdapter
                adapter.removeItem(viewHolder.adapterPosition)
                Log.e("POS ->", "${viewHolder.adapterPosition}")

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerViewAssociate)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_project, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionAddProject -> {
                Log.e("add", "add Project")
                true
            }
            R.id.actionSearchProject -> {
                Log.e("search", "search Project")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * setup view model and factory for associate
     */
    private fun setUpUIViewModel() {

        projectViewModel =
            ViewModelProvider(activity as AppCompatActivity).get(ProjectViewModel::class.java)
        projectViewModel.allProjects.observe(requireActivity(), Observer { it ->
            Log.e("PROJECT MUTABLE LIST->", "${it.size}")
            //it.addAll(it)

            if (it.size < 1) {
                Log.e("PROJECT IF->", "${it.size}")

                val list = arrayListOf(
                    "ATT FirstNet",
                    "Sawari Cab App",
                    "Resource Management App",
                    "ATT",
                    "TMobile",
                    "Bell Canada"
                )
                list.forEach { projectData ->
                    projectEntity = ProjectEntity(projectData)
                    it.add(projectEntity)
                }

                projectViewModel.insertListOfProjects(it)
                recyclerViewProject.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(activity)
                    addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }

                recyclerViewAdapter = ProjectAdapter(it, this)
                recyclerViewProject.adapter = recyclerViewAdapter

            } else {
                Log.e("PROJECT ELSE->", "${it.size}")

                recyclerViewProject.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(activity)
                    addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }

                recyclerViewAdapter = ProjectAdapter(it, this)
                recyclerViewProject.adapter = recyclerViewAdapter
            }
        })
    }

    /**
     * on item click of project
     */
    override fun onItemClick(projectEntity: ProjectEntity) {
        val intent = Intent(requireActivity(), ActivityAddProject::class.java)
        intent.putExtra("addOrUpdate", "update")
        intent.putExtra("projectName", projectEntity.projectName)
        startActivity(intent)
    }
}
