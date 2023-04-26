package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.miu.housing.db.Building
import com.miu.housing.db.Room

@Dao
interface RoomDao {

    @Query("SELECT * FROM ROOM WHERE status = 1 ORDER BY id DESC")
    suspend fun getAllRooms():List<Room>

    @Insert
    suspend fun addMultipleRoom(vararg room: Room)

    @Update
    suspend fun updateRoom(vararg room: Room)

    @Query("SELECT * FROM ROOM WHERE Room_no =:room_number")
    suspend fun getRoom(room_number: Int?):Room
}