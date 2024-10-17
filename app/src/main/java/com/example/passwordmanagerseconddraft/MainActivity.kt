package com.example.passwordmanagerseconddraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.passwordmanagerseconddraft.auth.AuthViewModel
import com.example.passwordmanagerseconddraft.screens.HomeScreenViewModel
import com.example.passwordmanagerseconddraft.ui.theme.PasswordManagerSecondDraftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeScreenViewModel = ViewModelProvider(owner = this)[HomeScreenViewModel::class.java]
        val authViewModel = ViewModelProvider(owner = this)[AuthViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            PasswordManagerSecondDraftTheme {



                MasterApp(
                    homeScreenViewModel,
                    authViewModel
                )
            }
        }
    }
}

