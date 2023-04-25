package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.miu.housing.db.Faq

@Dao
interface FaqDao {
    @Insert
    suspend fun addFaq(faq: Faq)
    @Query("Select * from Faq order by level desc")
    suspend fun getAllFaq(): List<Faq>
    @Update
    suspend fun updateFaq(faq: Faq)
}