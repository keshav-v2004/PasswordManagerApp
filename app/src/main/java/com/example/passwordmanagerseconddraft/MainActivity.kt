package com.example.passwordmanagerseconddraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.passwordmanagerseconddraft.screens.HomeScreenViewModel
import com.example.passwordmanagerseconddraft.ui.theme.PasswordManagerSecondDraftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeScreenViewModel = ViewModelProvider(owner = this)[HomeScreenViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            PasswordManagerSecondDraftTheme {



                MasterApp(
                    homeScreenViewModel
                )
            }
        }
    }
}

