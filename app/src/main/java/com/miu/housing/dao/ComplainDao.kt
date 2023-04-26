package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miu.housing.db.Complain

@Dao
interface ComplainDao {
    @Insert
    suspend fun addComplain(complain: Complain)

    @Query(" SELECT * FROM Complain ")
    suspend fun getAllComplain():List<Complain>

    @Insert
    suspend fun addMultipleComplain(vararg complain: Complain)

    @Query("Delete from Complain")
    suspend fun deleteAll()
}