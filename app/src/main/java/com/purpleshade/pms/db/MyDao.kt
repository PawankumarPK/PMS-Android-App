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
    val user: RoomUser

    //Save full detail of user in the list
    @get:Query("select * from userDB")
    val userFullDetail: List<RoomUser>

    //Insert Data into DB
    @Insert
    fun userDetails(user: RoomUser)

    //Insert data into record table
    @Insert
    fun userRecords(record: RoomRecord)

    @Query("DELETE FROM userDB")
    fun clearTable()
}