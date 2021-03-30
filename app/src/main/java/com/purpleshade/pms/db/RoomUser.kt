package com.purpleshade.pms.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by pawan on 26,January,2021
 */

@Entity(tableName = "userDB")
class RoomUser {

    @PrimaryKey
    var id: Int? = null

    @ColumnInfo(name = "userId")
    var userId: String? = null

    @ColumnInfo(name = "username")
    var username: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "appPassword")
    var appPassword: String? = null

    @ColumnInfo(name = "lockAppStatus")
    var lockAppStatus: String? = null

    @ColumnInfo(name = "token")
    var token: String? = null


}