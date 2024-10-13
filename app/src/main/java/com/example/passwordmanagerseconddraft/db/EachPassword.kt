package com.example.passwordmanagerseconddraft.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EachPassword(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    var source : String,
    var username_loginId : String,
    var password:String,
)
