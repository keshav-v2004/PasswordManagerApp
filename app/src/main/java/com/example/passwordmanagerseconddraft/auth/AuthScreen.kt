package com.example.passwordmanagerseconddraft.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanagerseconddraft.screens.AppHeading

@Composable
fun LoginPage(
    authViewModel: AuthViewModel,
    navigateToSignupPage : ()->Unit,
    navigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = authViewModel.appAuthState) {
        when(authViewModel.appAuthState){
            is AuthViewModel.AppAuthState.authenticated-> navigateToHomeScreen()
            is AuthViewModel.AppAuthState.Error -> Toast.makeText(
                context,
                (authViewModel.appAuthState as AuthViewModel.AppAuthState.Error).errorMessage,
                Toast.LENGTH_SHORT
            ).show()
            else->Unit
        }

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
                onClick = {

                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        authViewModel.login(email, password)
                        Toast.makeText(context , "Logging in" , Toast.LENGTH_SHORT).show()


                    }
                    else {
                        Toast.makeText(context , "Email or password cannot be empty" , Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Login")
            }

            TextButton(
                onClick = navigateToSignupPage
            ) {
                Text(text = "Don't have a account , Signup Now")
            }

        }
    }
}

@Composable
fun SignupPage(
    authViewModel: AuthViewModel,
    navigateToLoginPage: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = authViewModel.appAuthState) {
        when(authViewModel.appAuthState){
            is AuthViewModel.AppAuthState.authenticated -> navigateToHomeScreen()
            is AuthViewModel.AppAuthState.Error->Toast.makeText(
                context ,
                (authViewModel.appAuthState as AuthViewModel.AppAuthState.Error).errorMessage ,
                Toast.LENGTH_SHORT)
                .show()
            else->Unit
        }

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
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        authViewModel.signUp(email, password)

                        Toast.makeText(context , "Signing up" , Toast.LENGTH_SHORT).show()

                    }
                    else {
                        Toast.makeText(context , "Email or password cannot be empty" , Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "SignUp")
            }

            TextButton(
                onClick = navigateToLoginPage
            ) {
                Text(text = "Already have an account , Login Now")
            }

        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun preview() {
//    LoginPage(rememberNavController())

}