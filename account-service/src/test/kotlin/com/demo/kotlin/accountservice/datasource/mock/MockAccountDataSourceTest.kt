package com.demo.kotlin.employeeservice.datasource.mock

import com.demo.kotlin.accountservice.datasource.mock.MockAccountDataSource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockAccountDataSourceTest{
    private val datasource= MockAccountDataSource()
    @Test
    fun test_get_account(){
        //given
        var name:String="Julia"
        //when
        val account=datasource.getAccountDetailsByName(name)
        //then
        Assertions.assertNotNull(account)
    }
    @Test
    fun test_get_account_accountNo_notBlank(){
        //given

        //when
        val account=datasource.getAccountDetailsByAccountNumber(135)
        //then
        Assertions.assertNotNull(account.accountNumber)
    }
}