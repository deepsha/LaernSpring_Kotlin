package com.demo.kotlin.employeeservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get as get
import org.junit.jupiter.api.TestInstance.Lifecycle

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest  @Autowired constructor
    (val mockMvc:MockMvc,
     val objectMap:ObjectMapper){
   //constructor injection
   @Nested
   @DisplayName("GET /api/employees/all")
   @TestInstance(TestInstance.Lifecycle.PER_CLASS)
   inner class GetEmployeeDetails {

       val baseUrl = "/api/employees"
       @Test
       fun `should return all banks`() {
           // when/then
           mockMvc.get(baseUrl+"/all")
               .andDo { print() }
               .andExpect {
                   status { isOk() }
                   content { contentType(MediaType.APPLICATION_JSON) }
                   jsonPath("$[0].full_name") { value("David beckham") }
               }
       }
   }


}

