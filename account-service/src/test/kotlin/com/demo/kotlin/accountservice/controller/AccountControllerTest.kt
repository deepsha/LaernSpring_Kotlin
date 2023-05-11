package com.demo.kotlin.accountservice.controller

import com.demo.kotlin.accountservice.model.Account
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
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest @Autowired constructor
    (val mockMvc: MockMvc,
     val objectMapper: ObjectMapper) {

    val baseUrl = "/api/accounts"
    @Nested
    @DisplayName("GET /api/accounts/all")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetAccountDetails {

        @Test
        fun `should return all banks`() {
            // when/then
            mockMvc.get(baseUrl+"/all")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].account_number") { value("133") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/accounts/num/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetAccountDetail {

        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = 133

            // when/then
            mockMvc.get(baseUrl+"/num/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountHolder_name") { value("David beckham") }
                    jsonPath("$.account_type") { value("Savings") }
                }
        }

        @Test
        fun `should return NOT FOUND if the account number does not exist`() {
            // given
            val accountNumber = 123

            // when/then
            mockMvc.get(baseUrl+"/num/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/accounts")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostAccountDetails {

        @Test
        fun `should add the new bank`() {
            // given
            val newAccount = Account(140, "Hugh Jackman", "Current",1200.00,"pop")

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newAccount)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(newAccount))
                    }
                }

            mockMvc.get("$baseUrl/num/${newAccount.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(newAccount)) } }
        }

        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`() {
            // given
            val invalidAccount = Account(133, "Hugh Jackman", "Current",1200.00,"pop")

            // when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidAccount)
            }

            // then
            performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }

    @Nested
    @DisplayName("PATCH /api/accounts")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingBank {

        @Test
        fun `should update an existing bank`() {
            // given

            val updatedAccount = Account(140, "Hugh Grant", "Saving",1200.00,"pop")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedAccount)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedAccount))
                    }
                }

            mockMvc.get("$baseUrl/num/${updatedAccount.accountNumber}")
                .andExpect { content { json(objectMapper.writeValueAsString(updatedAccount)) } }
        }

        @Test
        fun `should return BAD REQUEST if no bank with given account number exists`() {
            // given

            val invalidAccount = Account(150, "Hugh Grant", "Saving",1200.00,"pop")

            // when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidAccount)
            }

            // then
            performPatchRequest
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("DELETE /api/accounts/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingBank {

        @Test
        @DirtiesContext
        fun `should delete the bank with the given account number`() {
            // given
            val accountNumber = 134

            // when/then
            mockMvc.delete("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/num/$accountNumber")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `should return NOT FOUND if no bank with given account number exists`() {
            // given
            val invalidAccountNumber = "does_not_exist"

            // when/then
            mockMvc.delete("$baseUrl$invalidAccountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}
