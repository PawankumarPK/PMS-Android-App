package com.purpleshade.pms.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by pawan on 26,January,2021
 */

@Dao
interface MyDao {

    //Save particular details
    @get:Query("select * from userDB")
    val user: User

    //Save full detail of user in the list
    @get:Query("select * from userDB")
    val userFullDetail: List<User>

    //Insert Data into DB
    @Insert
    fun userDetails(user: User)

    @Query("DELETE FROM userDB")
    fun clearTable()
}