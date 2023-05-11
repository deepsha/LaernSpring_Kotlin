package com.demo.kotlin.accountservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Account (
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

    //if u have val,default getter and setter wil b implemented and primary constructor is defined
    //since we define data so hashcode,toString,equals method are implemented.

