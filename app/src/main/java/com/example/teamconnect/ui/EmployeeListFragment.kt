package com.example.teamconnect.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.webkit.MimeTypeMap
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeeapp.ui.EmployeeAdapter
import com.example.teamconnect.BuildConfig
import com.example.teamconnect.R
import com.example.teamconnect.data.Employee
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_employee_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*

const val READ_FILE_REQUEST = 1

class EmployeeListFragment : Fragment() {

    private lateinit var viewModel: EmployeeListViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EmployeeListViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_import_data -> {
                importEmployee()
                true
            }

            R.id.menu_export_data -> {
                GlobalScope.launch {
                    exportEmployees()
                }
                true
            }

            R.id.menu_sign_out -> {
                auth = FirebaseAuth.getInstance()
                auth.signOut()
                auth.addAuthStateListener {
                    if (auth.currentUser == null) {
                        val currId = findNavController().currentDestination!!.id
                        if (currId == R.id.employeeListFragment) {
                            findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToLoginFragment())
                        }
                    }
                }

                true
            }

            R.id.menu_chat -> {
                findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToChatFragment())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNavigationDrawer()

        with(employee_list) {
            layoutManager = LinearLayoutManager(activity)
            adapter = EmployeeAdapter { show, id ->
                if (show) {
                    findNavController().navigate(
                        EmployeeListFragmentDirections.actionEmployeeListFragmentToShowFragment(id)
                    )
                } else {
                    findNavController().navigate(
                        EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFrgament(
                            id
                        )
                    )
                }

            }
        }

        add_employee.setOnClickListener {
            findNavController().navigate(
                EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeDetailFrgament(
                    0
                )
            )
        }

        viewModel.employees.observe(viewLifecycleOwner, {
            (employee_list.adapter as EmployeeAdapter).submitList(it)
            if (it.isEmpty())
                no_employee_record.visibility = View.VISIBLE
            else no_employee_record.visibility = View.INVISIBLE
        })
    }

    private fun setupNavigationDrawer() {
        val drawerLayout = activity!!.findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = activity!!.findViewById<NavigationView>(R.id.navigation_view)


        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()

            when (it.itemId) {

                R.id.menu_about_us -> {
                    findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToAboutFragment())
                    true
                }

                R.id.menu_contact_us -> {
                    findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToContactFragment())
                    true
                }

                R.id.menu_import_data -> {
                    importEmployee()
                    true
                }

                R.id.menu_export_data -> {
                    GlobalScope.launch {
                        exportEmployees()
                    }
                    true
                }

                R.id.menu_sign_out -> {
                    auth = FirebaseAuth.getInstance()
                    auth.signOut()
                    auth.addAuthStateListener {
                        if (auth.currentUser == null) {
                            val currId = findNavController().currentDestination!!.id
                            if (currId == R.id.employeeListFragment) {
                                findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToLoginFragment())
                            }
                        }
                    }

                    true
                }

                R.id.menu_chat -> {
                    findNavController().navigate(EmployeeListFragmentDirections.actionEmployeeListFragmentToChatFragment())
                    true
                }

                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    private suspend fun exportEmployees() {
        var csvFile: File? = null
        withContext(Dispatchers.IO) {
            csvFile = try {
                createFile(requireActivity(), Environment.DIRECTORY_DOCUMENTS, "csv")
            } catch (e: IOException) {
                Toast.makeText(
                    requireActivity(),
                    "Error occurred while creating the file. ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                null
            }

            csvFile?.printWriter()?.use { out ->
                val employees = viewModel.getEmployeeList()
                if (employees.isNotEmpty()) {
                    employees.forEach {
                        out.println(it.name + "," + it.role + "," + it.age + "," + it.gender)
                    }
                }
            }
        }

        withContext(Dispatchers.Main) {
            csvFile?.let {
                val uri = FileProvider.getUriForFile(
                    requireActivity(),
                    BuildConfig.APPLICATION_ID + ".fileprovider", it
                )
                launchFile(uri, "csv")
            }
        }
    }

    private fun launchFile(uri: Uri, ext: String) {
        val mimeType: String? = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(uri, mimeType)

//        if(intent.resolveActivity(requireActivity().packageManager) != null) {
//            startActivity(intent)
//        } else {
//            Toast.makeText(requireActivity(), "No app to read CSV file.", Toast.LENGTH_SHORT).show()
//        }

        startActivity(intent)

    }


    private fun importEmployee() {
        Intent(Intent.ACTION_GET_CONTENT).also { readFileIntent ->
            readFileIntent.addCategory(Intent.CATEGORY_OPENABLE)
            readFileIntent.type = "text/*"
            readFileIntent.resolveActivity(requireActivity().packageManager).also {
                startActivityForResult(readFileIntent, READ_FILE_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                READ_FILE_REQUEST -> {
                    GlobalScope.launch {
                        data?.data?.also { uri ->
                            readFromFile(uri)
                        }
                    }
                }
            }
        }
    }

    private suspend fun readFromFile(uri: Uri) {
        requireActivity().applicationContext.contentResolver
            .openFileDescriptor(uri, "r")?.use {
                withContext(Dispatchers.IO) {
                    FileInputStream(it.fileDescriptor).use {
                        parseCsvFile(it)
                    }
                }
            }
    }

    private suspend fun parseCsvFile(stream: FileInputStream) {
        val employees = mutableListOf<Employee>()

        BufferedReader(InputStreamReader(stream)).forEachLine {
            val tokens = it.split(",")
            val name = tokens[0]
            employees.add(Employee(0, name, 0, 0, 1, 0, ""))
//            employees.add(
//                Employee(
//                    id = 0, name = tokens[0], role = tokens[1].toInt(),
//                    age = tokens[2].toInt(), gender = tokens[3].toInt(), photo = ""
//                )
//            )

        }

        if (employees.isNotEmpty()) {
            viewModel.insertEmployees(employees)
        }
    }

}
