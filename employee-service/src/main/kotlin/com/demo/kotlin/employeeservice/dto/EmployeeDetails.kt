package com.demo.kotlin.employeeservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

class EmployeeDetails(
    @JsonProperty("full_name")
    val fullname:String,
    @JsonProperty("role")
    val role:String,
    @JsonProperty("age")
    val age:Int,
    @JsonProperty("department_name")
    val deptname:String,
    @JsonProperty("account_balance")
    var accountBalance: Double?
)
