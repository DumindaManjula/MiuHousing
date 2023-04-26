package com.miu.housing.db

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    foreignKeys = [ForeignKey(entity = Building::class,
        parentColumns = ["id"],
        childColumns = ["building_id"],
        onDelete = ForeignKey.CASCADE)])
data class Room(var roomNumber:String?, var roomType:String?, var price:Int? , var status:Boolean, var image: Bitmap?):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    @ColumnInfo(name = "building_id") val buildingId: Int = 0
}
