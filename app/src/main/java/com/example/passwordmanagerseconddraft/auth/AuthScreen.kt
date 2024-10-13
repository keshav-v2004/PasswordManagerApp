package com.example.passwordmanagerseconddraft.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginPage(
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "PASSWORD MANAGER APP")

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            }
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            }
        )

        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Login")
        }

        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Don't have a account , Signup Now")
        }

    }
}

@Composable
fun SignupPage(
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "PASSWORD MANAGER APP")

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            }
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            }
        )

        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Login")
        }

        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Don't have a account , Signup Now")
        }
    }
}