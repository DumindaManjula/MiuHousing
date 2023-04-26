package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Query
import com.miu.housing.db.Building
import com.miu.housing.db.Room

@Dao
interface BuildingDao {

    @Query("SELECT * FROM BUILDING ORDER BY id DESC")
    suspend fun getAllBuildings():List<Building>

}