package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.miu.housing.db.Booking

@Dao
interface BookingDao {

    @Insert
    suspend fun addBooking(booking:Booking)

    @Query("Delete from Booking")
    suspend fun deleteAllBooking()


    @Query("SELECT * FROM BOOKING WHERE user=:id")
    suspend fun getBooking(id:Int):Booking

    @Query("DELETE FROM BOOKING WHERE user=:id")
    suspend fun deleteBooking(id:Int)



}