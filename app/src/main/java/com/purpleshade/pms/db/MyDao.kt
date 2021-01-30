package com.purpleshade.pms.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by pawan on 26,January,2021
 */

@Dao
interface MyDao {

    @get:Query("select * from userDB")
    val user: User

    //Insert Data into DB
    @Insert
    fun addUser(user: User)

    @Query("DELETE FROM userDB")
    fun clearTable()
}