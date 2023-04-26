package com.miu.housing.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Building(var buildingName:String? ,var numberOfRooms:Int? , var image:Bitmap?):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
