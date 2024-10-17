package com.example.passwordmanagerseconddraft.db

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(
    entities = [EachPassword::class],
    version = 2,
    exportSchema = false
)
abstract class PasswordDatabase : RoomDatabase() {

    abstract fun getPasswordDao() : PasswordDao

    companion object{
        const val NAME = "Password_DB"
    }


}