package com.miu.housing.dao

import androidx.room.*
import com.miu.housing.db.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user:User)

    @Query(" SELECT * FROM USER ")
    suspend fun getAllUsers():List<User>

    @Query("select * from user where emailId= :emailId ")
    suspend fun getUserByEmailId(emailId: String) : User
}