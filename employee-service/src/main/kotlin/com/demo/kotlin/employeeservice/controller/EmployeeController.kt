package com.demo.kotlin.employeeservice.controller


import com.demo.kotlin.employeeservice.dto.EmployeeDetails
import com.demo.kotlin.employeeservice.model.AccountDetails
import com.demo.kotlin.employeeservice.model.Employee
import com.demo.kotlin.employeeservice.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/employees")
class EmployeeController ( val empService: EmployeeService){

    @GetMapping("/all")
    fun getAllEmployees(): Collection<Employee> = empService.getAllEmployees()
    @GetMapping("/role/{role}")
    fun getEmployeeByRole(@PathVariable role:String): Employee = empService.getEmployeeByRole(role)

    @GetMapping("/{employeeId}")
    fun getEmployeeAccountDetails(@PathVariable employeeId:String): EmployeeDetails = empService.getEmployeeAccountDetails(employeeId)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addAccountDetails(@RequestBody employee:Employee): Employee = empService.createEmployeeDetails(employee)
    //Path update is ideal for partial update for existing element whereas PUT is good for creation if item dont exist
    @PatchMapping
    fun updateEmployeeDetails(@RequestBody employee:Employee): Employee =empService.updateEmployeeDetails(employee)
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEmployeeDetailsById(@PathVariable employeeId:String):  Collection<Employee> = empService.deleteEmployeeDetailsById(employeeId)


}