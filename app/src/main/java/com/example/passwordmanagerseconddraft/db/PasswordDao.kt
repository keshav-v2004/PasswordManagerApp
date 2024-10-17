package com.example.passwordmanagerseconddraft.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.passwordmanagerseconddraft.auth.AuthViewModel

val authViewModel = AuthViewModel()

@Dao
interface PasswordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(password : EachPassword)

    @Query("SELECT * FROM EachPassword where currentUserId = :currentUserId")
    fun getAllPassword(currentUserId : String = authViewModel.auth.currentUser?.email.toString()) : LiveData<List<EachPassword>>

    @Query("DELETE FROM EACHPASSWORD WHERE id = :id")
    fun deleteNote(id:Int)


    @Query("update eachpassword set source = :newSrc , username_loginId = :NewLogin , password = :NewPass WHERE id = :id")
    fun updatePass(
        newSrc:String,
        NewLogin:String,
        NewPass:String,
        id: Int,
    )

    @Query("delete from eachpassword where currentUserId = :currentUserId")
    fun deleteAllRecordsForAnAccount(currentUserId: String)

}