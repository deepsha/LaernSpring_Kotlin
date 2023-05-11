package com.demo.kotlin.employeeservice.service

import com.demo.kotlin.employeeservice.datasource.mock.MockEmployeeDataSource
import com.demo.kotlin.employeeservice.dto.EmployeeDetails
import com.demo.kotlin.employeeservice.model.AccountDetails
import com.demo.kotlin.employeeservice.model.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class EmployeeService(@Autowired
                      private val restTemplate :RestTemplate,private val employeeDS: MockEmployeeDataSource) {

    fun getAllEmployees(): Collection<Employee> =  employeeDS.getAllEmployees()
    fun getEmployeeByRole(role: String):  Employee = employeeDS.getAllEmployeesByRole(role)
    fun getEmployeeByEmpId(empId: String):  Employee = employeeDS.getEmployeeByEmpId(empId)
    fun createEmployeeDetails(employee: Employee): Employee =employeeDS.createEmployeeDetails(employee)
    fun updateEmployeeDetails(employee: Employee): Employee =employeeDS.updateEmployeeDetails(employee)
    fun deleteEmployeeDetailsById(empId: String): Collection<Employee> =employeeDS.deleteEmployeeDetailsById(empId)
    fun getEmployeeAccountDetails(employeeId: String): EmployeeDetails {

        var existingCustomer=getEmployeeByEmpId(employeeId)
        val response :ResponseEntity<AccountDetails> =restTemplate.getForEntity("http://localhost:8081/api/accounts/num/${existingCustomer.accountNumber}")
        var balance=response.body?.accountBalance
        var employeeResponse=EmployeeDetails(existingCustomer.fullname,existingCustomer.role,existingCustomer.age,existingCustomer.deptname,balance)
        return employeeResponse
    }


}