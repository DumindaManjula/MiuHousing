package com.miu.housing.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(var firstName:String?, var lastName:String? , var emailId:String?, var password:String? ) :
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    }
