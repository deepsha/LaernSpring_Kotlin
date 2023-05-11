package com.demo.kotlin.accountservice.controller

import com.demo.kotlin.accountservice.model.Account
import com.demo.kotlin.accountservice.service.AccountService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("api/accounts")
class AccountController(val accountService: AccountService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e:NoSuchElementException):ResponseEntity<String>
    = ResponseEntity(e.message,HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(e:IllegalArgumentException):ResponseEntity<String>
            = ResponseEntity(e.message,HttpStatus.BAD_REQUEST)
    @GetMapping("/all")
    fun getAllAccountDetails(): Collection<Account> = accountService.getAllAccountDetails()
    @GetMapping("/name/{name}")
    fun getAccountDetails(@PathVariable name:String): Account= accountService.getAccountDetailsByName(name)
    @GetMapping("/num/{accountNum}")
    fun getAccountDetailsByAccountNumber(@PathVariable accountNum:Int): Account= accountService.getAccountDetailsByAccountNumber(accountNum)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addAccountDetails(@RequestBody account:Account): Account = accountService.addAccountDetails(account)
    //Path update is ideal for partial update for existing element whereas PUT is good for creation if item dont exist
    @PatchMapping
    fun updateAccountDetails(@RequestBody account:Account): Account =accountService.updateAccountDetails(account)
    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDetailsByAccountNumber(@PathVariable accountNumber:Int):  Collection<Account> = accountService.deleteDetailsByAccountNumber(accountNumber)

}