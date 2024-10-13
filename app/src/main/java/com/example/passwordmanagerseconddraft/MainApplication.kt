package com.example.passwordmanagerseconddraft

import android.app.Application
import androidx.room.Room
import com.example.passwordmanagerseconddraft.db.PasswordDatabase

class MainApplication : Application() {



    companion object{
        lateinit var passwordDatabase: PasswordDatabase
    }

    override fun onCreate() {
        super.onCreate()

        passwordDatabase = Room.databaseBuilder(
            applicationContext,
            PasswordDatabase::class.java,
            PasswordDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()


    }

}