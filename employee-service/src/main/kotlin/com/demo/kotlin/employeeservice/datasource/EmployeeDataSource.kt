package com.demo.kotlin.employeeservice.datasource

import com.demo.kotlin.employeeservice.model.Employee

interface EmployeeDataSource {
    fun getAllEmployees():Collection<Employee>
    fun getEmployeeByEmpId(empId:String):Employee
    fun getAllEmployeesByRole(role:String):Employee
    fun createEmployeeDetails(employee: Employee): Employee
    fun updateEmployeeDetails(employee: Employee): Employee
    fun deleteEmployeeDetailsById(empId: String): Collection<Employee>


}