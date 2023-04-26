package com.miu.housing.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Complaint(var content:String?, var email: String?):java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
