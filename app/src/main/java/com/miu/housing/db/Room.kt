package com.miu.housing.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Room(var roomNumber:String?, var roomType:String?, var price:Int? , var size:Double?, var status:String?, var image: Bitmap?, var buildingId:Int? ):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
