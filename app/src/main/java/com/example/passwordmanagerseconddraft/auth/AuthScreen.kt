package com.example.passwordmanagerseconddraft.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanagerseconddraft.Screens
import com.example.passwordmanagerseconddraft.screens.AppHeading

@Composable
fun LoginPage(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            AppHeading()
        }
    ) {paddingValues->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .statusBarsPadding()
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder = {
                    Text(text = "Enter email")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = "Enter password")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Login")
            }

            TextButton(
                onClick = {
                    navController.navigate(Screens.Signup.name)
                }
            ) {
                Text(text = "Don't have a account , Signup Now")
            }

        }
    }
}

@Composable
fun SignupPage(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            AppHeading()
        }
    ) {paddingValues->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .statusBarsPadding()
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder = {
                    Text(text = "Enter email")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = "Enter password")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "SignUp")
            }

            TextButton(
                onClick = {
                    navController.navigate(Screens.Login.name)
                }
            ) {
                Text(text = "Already have an account , Login Now")
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun preview() {
    LoginPage(rememberNavController())

}