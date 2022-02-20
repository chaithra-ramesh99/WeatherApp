package com.example.weatherapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp.model.User


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:User)

    @Query("SELECT * FROM user")
    fun getAllUserData():LiveData<List<User>>

    @Query("DELETE FROM user WHERE id = :userId")

    fun deleteUser(userId: Int)

}