package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import com.miu.housing.db.Booking

@Dao
interface BookingDao {

    @Insert
    suspend fun addBooking(booking:Booking)
}