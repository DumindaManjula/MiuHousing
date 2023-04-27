package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miu.housing.db.Booking
import com.miu.housing.db.Building
import com.miu.housing.db.Room
import com.miu.housing.db.User

@Dao
interface BuildingDao {

    @Query("SELECT * FROM BUILDING ORDER BY id DESC")
    suspend fun getAllBuildings():List<Building>

    @Query("Delete from Building")
    suspend fun deleteAllBuilding()


    @Insert
    suspend fun addMultipleBuilding(vararg building: Building)

}