package com.example.teamconnect.data

import android.app.Application
import androidx.lifecycle.LiveData

class EmployeeListRepository(context: Application) {
    private val employeeListDao: EmployeeListDao =
        EmployeeDatabase.getDatabase(context).employeeListDao()

    fun getEmployees() : LiveData<List<Employee>> {
        return employeeListDao.getEmployees()
    }

    suspend fun insertEmployees(employees: List<Employee>) {
        employeeListDao.insertEmployees(employees)
    }

    suspend fun getEmployeeList(): List<Employee> {
        return employeeListDao.getEmployeeList()
    }
}