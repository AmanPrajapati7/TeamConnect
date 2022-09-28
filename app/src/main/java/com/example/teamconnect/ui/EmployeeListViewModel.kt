package com.example.teamconnect.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.teamconnect.data.Employee
import com.example.teamconnect.data.EmployeeListRepository

class EmployeeListViewModel(application: Application): AndroidViewModel(application) {
    private val repo: EmployeeListRepository =
        EmployeeListRepository(application)

    val employees: LiveData<List<Employee>> = repo.getEmployees()

    suspend fun insertEmployees(employees: List<Employee>) {
        repo.insertEmployees(employees)
    }

    suspend fun getEmployeeList(): List<Employee> {
        return repo.getEmployeeList()
    }
}