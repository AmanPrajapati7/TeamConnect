package com.example.teamconnect.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Gender{
    Male,
    Female,
    Other
}

enum class  Role {
    Manager,
    Staff,
    Worker
}

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val role: Int,
    val age: Int,
    val gender: Int,
    val phone: Long,
    val photo: String
)