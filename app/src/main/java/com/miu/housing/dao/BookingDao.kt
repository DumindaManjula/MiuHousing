package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miu.housing.db.Booking

@Dao
interface BookingDao {

    @Insert
    suspend fun addBooking(booking:Booking)

    @Query("Delete from Booking")
    suspend fun deleteAllBooking()

}