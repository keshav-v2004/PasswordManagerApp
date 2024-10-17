package com.example.passwordmanagerseconddraft.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

var currentUserId :String? = null

class AuthViewModel : ViewModel() {


    val auth = FirebaseAuth.getInstance()

    sealed interface AppAuthState {

        object unauthenticated : AppAuthState
        object authenticated : AppAuthState
        object Loading:AppAuthState
        data class Error(val errorMessage : String) : AppAuthState
    }

    var appAuthState : AppAuthState by mutableStateOf(AppAuthState.unauthenticated)
        private set

    init {
        checkAuthStatus()

    }



    fun checkAuthStatus(){

        if (auth.currentUser==null){
            appAuthState = AppAuthState.unauthenticated
            currentUserId = null
            Log.i("current user is : " , currentUserId.toString())
        }
        else{
            appAuthState = AppAuthState.authenticated
            currentUserId = auth.currentUser!!.email.toString()
            Log.i("current user is : " , currentUserId.toString())


        }
    }

    fun login(email : String , password : String) {

        appAuthState = AppAuthState.Loading

        if (auth.currentUser == null) {

            viewModelScope.launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
                        if (task.isSuccessful){
                            appAuthState = AppAuthState.authenticated
                            currentUserId = task.result.user?.email
                            Log.i("current user is : " , currentUserId.toString())

                        }
                        else{
                            appAuthState = AppAuthState.Error(errorMessage = task.exception?.message.toString())
                        }

                    }

                }catch (e:Error){
                    appAuthState = AppAuthState.Error(e.message.toString())
                }
            }

        }
        else {
            appAuthState = AppAuthState.authenticated
        }



    }

    fun signUp(email : String , password : String) {

        appAuthState = AppAuthState.Loading

        if (auth.currentUser == null) {

            viewModelScope.launch {
                try {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
                        if (task.isSuccessful){
                            appAuthState = AppAuthState.authenticated
                            currentUserId = task.result.user?.email
                            Log.i("current user is : " , currentUserId.toString())

                        }
                        else{
                            appAuthState = AppAuthState.Error(errorMessage = task.exception?.message.toString())
                        }

                    }

                }catch (e:Error){
                    Log.i("loginError" , e.message.toString())
                    appAuthState = AppAuthState.Error(e.message.toString())
                }
            }

        }
        else {
            appAuthState = AppAuthState.authenticated
        }



    }

    fun signOut() {
        auth.signOut()
        appAuthState = AppAuthState.unauthenticated
        Log.i("current user is : " , currentUserId.toString())
    }

    fun deleteAcc(context: Context) {
        if (auth.currentUser != null) {
            try {
                auth.currentUser!!.delete().addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Deleting account",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                appAuthState = AppAuthState.unauthenticated

            }catch (e:Error){
                Toast.makeText(
                    context,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

}