package com.example.passwordmanagerseconddraft.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passwordmanagerseconddraft.screens.AppHeading

@Composable
fun LoginPage(
    authViewModel: AuthViewModel,
    navigateToSignupPage : ()->Unit,
    navigateToHomeScreen: () -> Unit,
    navigateToLoadingScreen :() ->Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    var isWelcomeDialogBoxVisible by rememberSaveable {
        mutableStateOf(false)
    }

    if(isWelcomeDialogBoxVisible){
        AlertDialog(
            onDismissRequest = { isWelcomeDialogBoxVisible = false },
            confirmButton = {
                Button(onClick = { isWelcomeDialogBoxVisible = false }) {
                    Text(text = "continue using app")
                }
            },
            title = {
                Text(text = "Welcome to password manager by Keshav Verma , Login Now")
            }
        )
    }

    LaunchedEffect(key1 = authViewModel.appAuthState) {
        when(authViewModel.appAuthState){
            is AuthViewModel.AppAuthState.authenticated-> navigateToHomeScreen()
            is AuthViewModel.AppAuthState.Error -> Toast.makeText(
                context,
                (authViewModel.appAuthState as AuthViewModel.AppAuthState.Error).errorMessage,
                Toast.LENGTH_SHORT
            ).show()
            is AuthViewModel.AppAuthState.Loading -> navigateToLoadingScreen()
            else->Unit
        }

    }

    Scaffold(
        topBar = {
            AuthScreenAppbar(
                accountIconOnAuthScreenClicked = { isWelcomeDialogBoxVisible = true }
            )
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
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = "Enter password")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        authViewModel.login(email, password)
                        navigateToLoadingScreen()
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
    navigateToLoadingScreen :() ->Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    var isWelcomeDialogBoxVisible by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = authViewModel.appAuthState) {
        when(authViewModel.appAuthState){
            is AuthViewModel.AppAuthState.authenticated -> navigateToHomeScreen()
            is AuthViewModel.AppAuthState.Error->Toast.makeText(
                context ,
                (authViewModel.appAuthState as AuthViewModel.AppAuthState.Error).errorMessage ,
                Toast.LENGTH_SHORT)
                .show()
            is AuthViewModel.AppAuthState.Loading -> navigateToLoadingScreen()
            else ->Unit
        }

    }

    if(isWelcomeDialogBoxVisible){
        AlertDialog(
            onDismissRequest = { isWelcomeDialogBoxVisible = false },
            confirmButton = {
                Button(onClick = { isWelcomeDialogBoxVisible = false }) {
                    Text(text = "continue using app")
                }
            },
            title = {
                Text(text = "Welcome to password manager by Keshav Verma , Signup Now")
            }
        )
    }

    Scaffold(
        topBar = {
            AuthScreenAppbar(
                accountIconOnAuthScreenClicked = { isWelcomeDialogBoxVisible = true }
            )
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
                singleLine = true,
                placeholder = {
                    Text(text = "Enter email")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                singleLine = true,
                placeholder = {
                    Text(text = "Enter password")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        authViewModel.signUp(email, password)
                        navigateToLoadingScreen()
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
    navigateToHomeScreen: () -> Unit,
    navigateToLoginPage: () -> Unit,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(key1 = authViewModel.appAuthState) {
        when(authViewModel.appAuthState){
            is AuthViewModel.AppAuthState.authenticated -> navigateToHomeScreen()
            is AuthViewModel.AppAuthState.unauthenticated -> navigateToLoginPage()
            is AuthViewModel.AppAuthState.Error -> {
                navigateToLoginPage()
            }
            else -> Unit
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreenAppbar(
    accountIconOnAuthScreenClicked  :()->Unit,
    modifier: Modifier = Modifier
) {
    MediumTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Password Manager",
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.width(110.dp))

                IconButton(
                    onClick = accountIconOnAuthScreenClicked
                ) {
                    Icon(
                        Icons.Default.AccountBox,
                        null
                    )
                }

            }

        },
        colors = TopAppBarColors(
            containerColor = Color.Magenta,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        ),
    )
}
