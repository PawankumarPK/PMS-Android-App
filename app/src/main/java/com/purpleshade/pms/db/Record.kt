package com.purpleshade.pms.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.purpleshade.pms.model.RecordList

/**
 * Created by pawan on 26,January,2021
 */

@Entity(tableName = "records")
class Record {

    @PrimaryKey
    var id: Int? = null

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "password")
    var password: String? = null

    @ColumnInfo(name = "addNote")
    var addNote: String? = null

    @ColumnInfo(name = "websiteAddress")
    var websiteAddress: String? = null

    @ColumnInfo(name = "loginId")
    var loginId: String? = null


}