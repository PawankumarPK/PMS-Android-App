package com.purpleshade.pms.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by pawan on 26,January,2021
 */

@Dao
interface MyDao {

    //Save particular details
    @get:Query("select * from userDB")
    val user: RoomUser

    //Insert Data into User table
    @Insert
    fun userDetails(user: RoomUser)

    //Update username column in user table
    @Query("UPDATE userDB SET username = :username WHERE id =:id")
    fun usernameUpdate(username: String?, id: Int)

    @Query("SELECT * FROM records WHERE recordId=:id ")
    fun loadSingle(id: String): List<RoomRecord>

    @get:Query("select * from records")
    val records: List<RoomRecord>

    //Insert data into Record table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun userRecords(record: RoomRecord)

    @Query("DELETE FROM userDB")
    fun clearTable()

    @Query("DELETE FROM records WHERE recordId = :recordId")
    fun deleteByRecordId(recordId: String)
}