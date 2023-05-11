package com.demo.kotlin.employeeservice.datasource.mock


import com.demo.kotlin.employeeservice.datasource.EmployeeDataSource
import com.demo.kotlin.employeeservice.model.Employee
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository("mock")
class MockEmployeeDataSource: EmployeeDataSource {
    var employeeList = mutableListOf<Employee>(
        Employee( "A130","David beckham", "Manager",50,"CRM",133),
        Employee( "A131","Julia", "VP",55,"CRM",134),
        Employee( "A132","Peter pan","Manager",50,"CRM",135),
        Employee("A133","Charlie chaplin", "HR",40,"CRM",136)
    )

    override fun getAllEmployees(): Collection<Employee> = employeeList
    override fun getEmployeeByEmpId(empId: String): Employee = employeeList.firstOrNull(){ it.employeeId==empId}
        ?:throw NoSuchElementException("Could not find employee for $empId")

    override fun getAllEmployeesByRole(role: String): Employee = employeeList.firstOrNull(){ it.role==role}
        ?:throw NoSuchElementException("Could not find employee for $role")

   override fun createEmployeeDetails(employee: Employee): Employee {
        if (employeeList.any{it.employeeId==employee.employeeId}) {
            throw IllegalArgumentException("Employee for ${employee.employeeId} already exist! ")
        }
        employeeList.add(employee)
        return employee
    }

    override fun updateEmployeeDetails(employee: Employee): Employee {
        var existingEmployee=employeeList.firstOrNull(){ it.employeeId==employee.employeeId}
            ?: throw NoSuchElementException("Could not find a employee with id ${employee.employeeId}")
        employeeList.remove(existingEmployee)
        employeeList.add(employee)
        return employee
    }

    override fun deleteEmployeeDetailsById(empId: String): Collection<Employee> {
        var existingEmployee=employeeList.firstOrNull(){ it.employeeId==empId}
            ?: throw NoSuchElementException("Could not find a employee with id: ${empId}")
        employeeList.remove(existingEmployee)
        return employeeList
    }


}
