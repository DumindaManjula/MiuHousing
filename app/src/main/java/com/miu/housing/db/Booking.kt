package com.miu.housing.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Booking(var buildingName:String?,var room_number:Int? ,var userId:Int?, var status:Boolean):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
