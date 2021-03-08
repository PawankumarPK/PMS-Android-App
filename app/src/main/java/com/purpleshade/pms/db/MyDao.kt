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

    //Update app pin column in user table
    @Query("UPDATE userDB SET appPassword = :appPin WHERE id =:id")
    fun pinUpdate(appPin: String?, id: Int)

    //Update lock app status column in user table
    @Query("UPDATE userDB SET lockAppStatus = :lockAppStatus WHERE id =:id")
    fun lockAppStatus(lockAppStatus: String?, id: Int)


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