package com.miu.housing.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Booking(var buildingId:Int?,var roomId:Int? ,var userId:Int?, var status:String?):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
