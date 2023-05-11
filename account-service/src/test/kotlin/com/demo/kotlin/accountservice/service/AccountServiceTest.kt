package com.demo.kotlin.accountservice.service

import com.demo.kotlin.accountservice.datasource.AccountDataSource
import com.demo.kotlin.accountservice.datasource.mock.MockAccountDataSource
import com.demo.kotlin.accountservice.model.Account
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test as Test

internal class AccountServiceTest{
 private val datasource:MockAccountDataSource=mockk(relaxed=true)
 private val accountDS=AccountService(datasource)

@Test
fun test_account_db(){

 //given
 every { datasource.getAllAccountDetails() } returns emptyList()
 //when

 val accounts=accountDS.getAllAccountDetails()

 //then
 verify(exactly = 1) { datasource.getAllAccountDetails() }
}
}