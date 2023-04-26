package com.miu.housing.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class Room(var buildingName:String, var Room_no:Int?, var Room_type:String?, var price:Int? , var status:Boolean, var image: Int):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

}
