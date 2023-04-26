package com.miu.housing.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Damage(var itemName:String? ,var reason:String? , var condition: String?, var email: String?):
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
