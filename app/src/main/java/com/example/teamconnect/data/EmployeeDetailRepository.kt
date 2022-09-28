package com.example.teamconnect.data

import android.content.Context
import androidx.lifecycle.LiveData

class EmployeeDetailRepository(context: Context) {
    private val employeeDetailDao: EmployeeDetailDao = EmployeeDatabase.getDatabase(context).employeeDetailDao()

    fun getEmployee(id: Long): LiveData<Employee> {
        return employeeDetailDao.getEmployee(id)
    }

    suspend fun insertEmployee(employee: Employee): Long {
        return employeeDetailDao.insertEmployee(employee)
    }

    suspend fun updateEmployee(employee: Employee) {
        employeeDetailDao.updateEmployee(employee)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDetailDao.deleteEmployee(employee)
    }
}