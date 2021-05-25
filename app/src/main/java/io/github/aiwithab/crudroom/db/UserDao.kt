package io.github.aiwithab.crudroom.db

import androidx.room.*
import io.github.aiwithab.crudroom.models.UserModel

@Dao
interface UserDao {


    @Insert
    fun insertUser(user: UserModel)

    @Query("SELECT * FROM user")
    fun gelAllUsers(): List<UserModel>

    @Update
    fun updateUser(user: UserModel)

    @Delete
    fun deleteUser(user: UserModel)


}