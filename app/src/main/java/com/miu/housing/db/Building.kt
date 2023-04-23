package com.miu.housing.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Building(var buildingNumber:String? ,var location:String? , var image:Bitmap?):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
