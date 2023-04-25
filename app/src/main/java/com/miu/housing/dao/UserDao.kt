package com.miu.housing.dao

import androidx.room.*
import com.miu.housing.db.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user:User)

    @Insert
    suspend fun addMultipleUsers(vararg user: User)

    @Query(" SELECT * FROM USER ")
    suspend fun getAllUsers():List<User>

    @Query("select * from user where emailId= :emailId and password= :password ")
    suspend fun getUserById(emailId: String, password: String) : User

    @Query("Select * from user where emailId = :emailId")
    suspend fun getUserByEmailId(emailId: String): User
}