package com.miu.housing.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.miu.housing.db.Damage
import com.miu.housing.db.User


@Dao
interface DamageDao {

    @Insert
    suspend fun addDamage(damage: Damage)

    @Query(" SELECT * FROM DAMAGE ")
    suspend fun getAllDamage():List<Damage>

    @Insert
    suspend fun addMultipleDamages(vararg damage: Damage)

    @Query("Delete from Damage")
    suspend fun deleteAllDamage()
}