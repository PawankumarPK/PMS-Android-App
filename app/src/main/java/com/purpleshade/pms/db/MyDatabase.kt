package com.purpleshade.pms.db

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Created by pawan on 26,January,2021
 */

@Database(entities = [RoomUser::class, RoomRecord::class], version = 1)

abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}