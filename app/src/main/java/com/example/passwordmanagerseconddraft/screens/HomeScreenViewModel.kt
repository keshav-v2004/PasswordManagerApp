package com.example.passwordmanagerseconddraft.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanagerseconddraft.MainApplication
import com.example.passwordmanagerseconddraft.db.EachPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    val passwordDao = MainApplication.passwordDatabase.getPasswordDao()

    fun insertNewPassword(eachPassword: EachPassword) {

        viewModelScope.launch(Dispatchers.IO) {
            passwordDao.insert(
                password = eachPassword
            )

        }
    }

    fun getAllPasswords() : LiveData<List<EachPassword>> {
        return passwordDao.getAllPassword()
    }

    fun deletePassword(id : Int){

        viewModelScope.launch(Dispatchers.IO) {
            passwordDao.deleteNote(id)
        }

    }

    fun updatePassword(
        newSrc:String,
        newLogin:String,
        newPass:String,
        id: Int
    ){
        viewModelScope.launch(Dispatchers.IO) {
            passwordDao.updatePass(
                newSrc,newLogin,newPass,id
            )
        }
    }

}