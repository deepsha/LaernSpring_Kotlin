package com.demo.kotlin.employeeservice.model

import com.fasterxml.jackson.annotation.JsonProperty

class AccountDetails (
    @JsonProperty("account_number")
    var accountNumber:Int,
    @JsonProperty("accountHolder_name")
    var accountHolderName:String,
    @JsonProperty("account_type")
    var accountType:String,
    @JsonProperty("account_balance")
    var accountBalance:Double,
    @JsonProperty("account_branch")
    var accountBranch:String
)
