package com.miu.housing.dao

import java.io.Serializable

data class User(var firstName:String?, var lastName:String? , var emailId:String?, var password:String? ) :
    Serializable {
        override fun toString(): String {
            return "User(firstName=$firstName, lastName=$lastName, emailId=$emailId, password=$password)"
        }
    }
