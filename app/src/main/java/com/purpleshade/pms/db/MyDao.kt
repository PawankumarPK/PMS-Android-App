package com.purpleshade.pms.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.purpleshade.pms.model.Records

/**
 * Created by pawan on 26,January,2021
 */

@Dao
interface MyDao {

    //Save particular details
    @get:Query("select * from userDB")
    val user: RoomUser

    @Query("SELECT * FROM records WHERE loginId=:id ")
    fun loadSingle(id: String): List<RoomRecord>

    @get:Query("select * from records")
    val records: List<RoomRecord>


    //Insert Data into User table
    @Insert
    fun userDetails(user: RoomUser)

    //Insert data into Record table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userRecords(record: RoomRecord)

    @Query("DELETE FROM userDB")
    fun clearTable()
}