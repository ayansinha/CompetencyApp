package org.dtransform.competencyapp.ui.associate.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_associate.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.dtransform.competencyapp.R
import org.dtransform.competencyapp.data.local.entity.AssociateEntity
import org.dtransform.competencyapp.data.local.entity.ProjectEntity
import org.dtransform.competencyapp.ui.associate.viewmodel.AssociateViewModel
import org.dtransform.competencyapp.ui.project.viewmodel.ProjectViewModel


/**
 * @activity{ActivityAddAssociate} -> view for associate
 */

class ActivityAddAssociate : AppCompatActivity() {


    //private var associateEntity: AssociateEntity? = null
    private lateinit var associateViewModel: AssociateViewModel
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var associateEntity: AssociateEntity
    private var projectList: MutableList<String> = ArrayList()
    //private var projectArray = emptyArray<String>()
    //private var projectArray = Array<String>(0) { _ -> "" }
    private var isComingFrom: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_associate)
        val intent = intent

        isComingFrom = intent.getStringExtra("addOrUpdate") //if add or update

        associateViewModel = ViewModelProviders.of(this).get(AssociateViewModel::class.java)
        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)

        initUI(intent)
    }

    private fun initUI(intent: Intent) {


        //projectArray = arrayOf("")
        projectList.clear()
        progressBarAssociate.visibility = View.GONE
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val spinner = findViewById<Spinner>(R.id.spinnerCurrentProjects)
        //val linearLayout = findViewById<LinearLayout>(R.id.spinnerContainer)
        //val spinner = Spinner(this)
        /*spinner.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )*/

        val id = inputAssociateID.text
        val name = inputAssociateName.text
        val band = inputAssociateBand.text
        val designation = inputAssociateDesignation.text

        var currentProject: String? = null
        var competency: String? = null

        if (isComingFrom.equals("update")) {
            val updateID = intent.getStringExtra("id")
            val updateName = intent.getStringExtra("name")
            val updateBand = intent.getStringExtra("band")
            val updateDesignation = intent.getStringExtra("designation")
            val updateCompetency = intent.getStringExtra("competency")
            val updateCP = intent.getStringExtra("cp")

            inputAssociateID.setText(updateID)
            inputAssociateName.setText(updateName)
            inputAssociateBand.setText(updateBand)
            inputAssociateDesignation.setText(updateDesignation)
            if (updateCompetency.toString().equals("Android")) {
                competency = "Android"
                radioGroup.check(R.id.radioButtonAndroid)
            } else if (updateCompetency.toString().equals("iOS")) {
                competency = "iOS"
                radioGroup.check(R.id.radioButtoniOS)
            } else if (updateCompetency.toString().equals("UX")) {
                competency = "UX"
                radioGroup.check(R.id.radioButtonUX)
            } else if (updateCompetency.toString().equals("Tester")) {
                competency = "Tester"
                radioGroup.check(R.id.radioButtonTester)
            }

            val index = getIndex(spinner, updateCP)
            Log.e("INDEX POS = ", "$index")
            Log.e("INDEX VAL = ", "$updateCP")
            spinner.setSelection(index)

        }

        radioGroup.setOnCheckedChangeListener { group, checkID ->

            competency = when {
                R.id.radioButtonAndroid == checkID -> {
                    "Android"
                }
                R.id.radioButtoniOS == checkID -> {
                    "iOS"
                }
                R.id.radioButtonUX == checkID -> {
                    "UX"
                }
                R.id.radioButtonTester == checkID -> {
                    "Tester"
                }
                else -> {
                    null
                }
            }
            //Toast.makeText(applicationContext, currentProject, Toast.LENGTH_SHORT).show()
        }
        projectViewModel.allProjects.observe(this, Observer {


            for (i in 0 until it.size) {
                //projectArray = arrayOf(it[i].projectName)
                /*projectArray = Array(arr.size) {
                    arr[i].projectName
                }*/
                //projectArray[i] = arr[i].projectName
            }
            it.forEach { item ->
                //projectArray = arrayOf(item.projectName)
                projectList.add(item.projectName)
            }
            Log.e("IT SIZE", "${it.size}")
            Log.e("Project SIZE", "${projectList.size}")
            Log.e("Project SIZE", "${projectList.size}")
            //Log.e("Project array", "$projectArray")

        })



        val spinnerArray:Array<String> = arrayOf("ATT FirstNet",
            "Sawari Cab App",
            "Resource Management App",
            "ATT",
            "TMobile",
            "Bell Canada")
        //val spinnerArray = arrayOfNulls<String>(projectList.size)
        //projectList.toTypedArray()

        //Log.e("SPINNER ARRAY", spinnerArray.toString())
        //Log.e("SPINNER ARRAY SIZE ", "" + spinnerArray.size)
        //if (spinner != null) {
        /*val array = arrayOfNulls<String>(list.size)
        list.toArray(array)*/
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArray)
        //spinner.adapter = ArrayAdapter(this@ActivityAddAssociate, android.R.layout.simple_spinner_dropdown_item, projectArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                currentProject = spinnerArray[position]
                Log.e("position : ", spinnerArray[position])
                Log.e("position : ", currentProject)
                Log.e("position ", parent.getItemAtPosition(position).toString())

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }


        //linearLayout?.addView(spinner)
        //}

        buttonSubmitAssociate.setOnClickListener {
            if ((!TextUtils.isEmpty(id)) && (!TextUtils.isEmpty(name)) && (!TextUtils.isEmpty(band)) && (!TextUtils.isEmpty(
                    designation
                ) && (!TextUtils.isEmpty(competency)) && (!TextUtils.isEmpty(currentProject)))
            ) {

                progressBarAssociate.visibility = View.VISIBLE
                runBlocking {
                    launch {
                        delay(2000)
                    }
                }


                associateViewModel.insert(
                    AssociateEntity(
                        id.toString(),
                        name.toString(),
                        band.toString(),
                        designation.toString(),
                        competency.toString(),
                        currentProject.toString()
                    )
                )

                progressBarAssociate.visibility = View.GONE
                finish()

            } else {
                Toast.makeText(this, "Please fill up all details", Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun getIndex(spinner: Spinner, myString: String): Int {
        var index = 0
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == myString) {
                index = i
            }
        }
        return index
    }


}