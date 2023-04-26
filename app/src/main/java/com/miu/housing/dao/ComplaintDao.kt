package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miu.housing.db.Complaint
import com.miu.housing.db.Damage

@Dao
interface ComplaintDao {
    @Insert
    suspend fun addComplaint(complaint: Complaint)

    @Query(" SELECT * FROM Complaint ")
    suspend fun getAllComplaint():List<Complaint>

    @Insert
    suspend fun addMultipleComplaint(vararg complaint: Complaint)

    @Query("Delete from Complaint")
    suspend fun deleteAll()
}