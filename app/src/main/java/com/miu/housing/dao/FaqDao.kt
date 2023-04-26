package com.miu.housing.dao

import androidx.room.*
import com.miu.housing.db.Faq

@Dao
interface FaqDao {
    @Insert
    suspend fun addFaq(faq: Faq)
    @Query("Select * from Faq order by level desc")
    suspend fun getAllFaqs(): List<Faq>
    @Update
    suspend fun updateFaq(faq: Faq)
    @Delete
    suspend fun deleteFaq(faq: Faq)
}