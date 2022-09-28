package com.example.employeeapp.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teamconnect.R
import com.example.teamconnect.data.Employee
import com.example.teamconnect.data.Gender
import com.example.teamconnect.data.Role
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_employee_detail.*
import kotlinx.android.synthetic.main.fragment_employee_list.*
import kotlinx.android.synthetic.main.list_item.*

class EmployeeAdapter(private val listener: (Boolean, Long) -> Unit) :
    ListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return EmployeeViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EmployeeViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            itemView.setOnClickListener {
                listener.invoke(true, getItem(adapterPosition).id)
            }

            edit_employee.setOnClickListener {
                listener.invoke(false, getItem(adapterPosition).id)
            }
        }

        fun bind(employee: Employee) {
            with(employee) {

                item_name.text = name
                item_age.text = age.toString()

                if (photo.isNotEmpty()) {
                    item_photo.setImageURI(Uri.parse(photo))
                } else {
                    item_photo.setImageResource(R.drawable.ic_baseline_person_24)
                }

                when (gender) {
                    Gender.Male.ordinal -> {
                        item_gender.text = Gender.Male.name
                    }
                    Gender.Female.ordinal -> {
                        item_gender.text = Gender.Female.name
                    }
                    Gender.Other.ordinal -> {
                        item_gender.text = Gender.Other.name
                    }
                }

                when (role) {
                    Role.Manager.ordinal -> {
                        item_role.text = Role.Manager.name
                    }
                    Role.Staff.ordinal -> {
                        item_role.text = Role.Staff.name
                    }
                    Role.Worker.ordinal -> {
                        item_role.text = Role.Worker.name
                    }
                }
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Employee>() {

        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }

}