package com.example.teamconnect.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.teamconnect.R
import com.example.teamconnect.data.Employee
import com.example.teamconnect.data.Gender
import com.example.teamconnect.data.Role
import kotlinx.android.synthetic.main.fragment_show.*
import androidx.appcompat.app.AppCompatActivity




class ShowFragment : Fragment() {

    private lateinit var viewModel: EmployeeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        viewModel = ViewModelProviders.of(this).get(EmployeeDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        show_photo.setImageResource(R.drawable.ic_baseline_person_24)
        show_photo.tag = ""

        val id = ShowFragmentArgs.fromBundle(requireArguments()).id
        viewModel.setEmployeeId(id)
        
        viewModel.employee.observe(viewLifecycleOwner, {
            setData(it)
        })

    }

    private fun setData(employee: Employee) {
        with(employee.photo) {
            if(isNotBlank()) {
                show_photo.setImageURI(Uri.parse(this))
                show_photo.tag = this
            } else {
                show_photo.setImageResource(R.drawable.ic_baseline_person_24)
                show_photo.tag = this
            }
        }

        collapsing_toolbar.title = employee.name

        show_age.setText((employee.age+18).toString())
        show_phone.setText(employee.phone.toString())
        when(employee.gender) {
            Gender.Male.ordinal -> {
                show_gender.setText("Male")
            }

            Gender.Female.ordinal -> {
                show_gender.setText("Female")
            }

            Gender.Other.ordinal -> {
                show_gender.setText("Other")
            }
        }

        when(employee.role) {
            Role.Manager.ordinal -> {
                show_role.setText("Manager")
            }

            Role.Staff.ordinal -> {
                show_role.setText("Staff")
            }

            Role.Worker.ordinal -> {
                show_role.setText("Worker")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}