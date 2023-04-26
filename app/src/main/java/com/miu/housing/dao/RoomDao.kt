package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Query
import com.miu.housing.db.Room

@Dao
interface RoomDao {

    @Query("SELECT * FROM ROOM ORDER BY roomNumber DESC")
    suspend fun getAllRooms():List<Room>

    @Query("Delete from Room")
    suspend fun deleteAllRoom()
}