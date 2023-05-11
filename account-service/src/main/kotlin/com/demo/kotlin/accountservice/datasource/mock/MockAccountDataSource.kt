package com.demo.kotlin.accountservice.datasource.mock

import com.demo.kotlin.accountservice.datasource.AccountDataSource
import com.demo.kotlin.accountservice.model.Account
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository("mock")
class MockAccountDataSource:AccountDataSource {
    var accountsList = mutableListOf<Account>(
        Account(133, "David beckham", "Savings",3000.0,"abc"),
        Account(134, "Julia", "Savings",3000.0,"abc"),
        Account(135, "Peter pan", "Savings",3000.0,"abc"),
        Account(136, "Charlie chaplin", "Current",3000.0,"abc")
    )

    override fun getAllAccountDetails(): Collection<Account> = accountsList
    override fun getAccountDetailsByName(name: String): Account =accountsList.firstOrNull(){ it.accountHolderName==name}
        ?:throw NoSuchElementException("Could not find account holder name $name")
    override fun getAccountDetailsByAccountNumber(accountNumber: Int): Account =accountsList.firstOrNull(){ it.accountNumber==accountNumber}
        ?:throw NoSuchElementException("Could not find account number $accountNumber")

    override fun addAccountDetails(account: Account): Account {
        if (accountsList.any{it.accountNumber==account.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${account.accountNumber} already exist! ")
        }
        accountsList.add(account)
        return account
    }

    override fun updateAccountDetails(account: Account): Account {
        var existingAcc=accountsList.firstOrNull(){ it.accountNumber==account.accountNumber}
            ?: throw NoSuchElementException("Could not find a bank with account number ${account.accountNumber}")
        accountsList.remove(existingAcc)
        accountsList.add(account)
        return account
    }

    override fun deleteDetailsByAccountNumber(accountNumber: Int): Collection<Account> {
        var existingAcc=accountsList.firstOrNull(){ it.accountNumber==accountNumber}
            ?: throw NoSuchElementException("Could not find a bank with account number ${accountNumber}")
        accountsList.remove(existingAcc)
        return accountsList
    }
}
