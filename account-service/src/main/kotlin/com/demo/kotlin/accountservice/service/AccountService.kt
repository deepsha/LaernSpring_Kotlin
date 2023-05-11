package com.demo.kotlin.accountservice.service

import com.demo.kotlin.accountservice.datasource.mock.MockAccountDataSource
import com.demo.kotlin.accountservice.model.Account
import org.springframework.stereotype.Service


@Service
class AccountService(private val accountDS: MockAccountDataSource) {

    fun getAccountDetailsByName(acctHolderName: String): Account = accountDS.getAccountDetailsByName(acctHolderName)
    fun getAccountDetailsByAccountNumber(accountNum: Int): Account =accountDS.getAccountDetailsByAccountNumber(accountNum)
    fun getAllAccountDetails(): Collection<Account> =accountDS.getAllAccountDetails()
    fun addAccountDetails(account: Account): Account =accountDS.addAccountDetails(account)
    fun updateAccountDetails(account: Account): Account =accountDS.updateAccountDetails(account)
    fun deleteDetailsByAccountNumber(accountNumber: Int): Collection<Account> =accountDS.deleteDetailsByAccountNumber(accountNumber)



}