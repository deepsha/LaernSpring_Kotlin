package com.demo.kotlin.accountservice.datasource

import com.demo.kotlin.accountservice.model.Account

interface AccountDataSource {
    fun getAccountDetailsByName(name: String):Account
    fun getAccountDetailsByAccountNumber(accountNumber: Int): Account
    fun getAllAccountDetails(): Collection<Account>
    fun addAccountDetails(account: Account): Account
    fun updateAccountDetails(account: Account): Account
    fun deleteDetailsByAccountNumber(accountNumber: Int): Collection<Account>




}