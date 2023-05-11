package com.demo.kotlin.employeeservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Employee(
    @JsonProperty("emp_id")
    val employeeId:String,
     @JsonProperty("full_name")
     val fullname:String,
     @JsonProperty("role")
     val role:String,
     @JsonProperty("age")
     val age:Int,
     @JsonProperty("department_name")
     val deptname:String,
    @JsonProperty("account_number")
    val accountNumber:Int)