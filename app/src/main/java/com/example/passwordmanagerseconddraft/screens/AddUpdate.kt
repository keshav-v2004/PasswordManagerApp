package com.example.passwordmanagerseconddraft.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.passwordmanagerseconddraft.db.EachPassword
import com.example.passwordmanagerseconddraft.db.authViewModel

@Composable
fun AddScreen(
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    var source by rememberSaveable {
        mutableStateOf("")
    }

    var username_Loginid by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppHeading(
                accountIconClicked = {}
            )
        }
    ) { paddingValues->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(paddingValues)
                .statusBarsPadding()
                .fillMaxWidth()

        ) {
            OutlinedTextField(
                value = source,
                onValueChange = {
                    source = it
                },
                placeholder = {
                    Text(
                        text = "enter source",
                        fontWeight = Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = username_Loginid,
                onValueChange = {
                    username_Loginid = it
                },
                placeholder = {
                    Text(
                        text = "enter username/LoginId",
                        fontWeight = Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )

            )
            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(
                        text = "enter password",
                        fontWeight = Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    if (source.isNotEmpty() && username_Loginid.isNotEmpty() && password.isNotEmpty()){
                        homeScreenViewModel.insertNewPassword(
                            EachPassword(
                                source = source,
                                username_loginId = username_Loginid,
                                password = password,
                                currentUserId = authViewModel.auth.currentUser?.email
                            )
                        )
                        navController.popBackStack()
                        Toast.makeText(context , "Password Saved Successfully" , Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(context , "above field(s) cannot be empty" , Toast.LENGTH_LONG).show()
                    }


                }
            ) {
                Text(
                    text = "SAVE",
                    fontWeight = Bold
                )
            }
        }
    }
}

@Composable
fun UpdateScreen(
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,
    password: EachPassword,
    modifier: Modifier = Modifier
) {

    var source by rememberSaveable {
        mutableStateOf(password.source)
    }

    var username_Loginid by rememberSaveable {
        mutableStateOf(password.username_loginId)
    }

    var actualPassword by rememberSaveable {
        mutableStateOf(password.password)
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppHeading(
                accountIconClicked = {}
            )
        }
    ) { paddingValues->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(paddingValues)
                .statusBarsPadding()
                .fillMaxWidth()

        ) {
            OutlinedTextField(
                value = source,
                onValueChange = {
                    source = it
                },
                placeholder = {
                    Text(
                        text = "enter updated source",
                        fontWeight = Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = username_Loginid,
                onValueChange = {
                    username_Loginid = it
                },
                placeholder = {
                    Text(
                        text = "enter updated username/LoginId",
                        fontWeight = Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(30.dp))

            OutlinedTextField(
                value = actualPassword,
                onValueChange = {
                    actualPassword = it
                },
                placeholder = {
                    Text(
                        text = "enter updated password",
                        fontWeight = Bold
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            Spacer(Modifier.height(30.dp))

            OutlinedButton(
                onClick = {
                    if (source.isNotEmpty() && actualPassword.isNotEmpty() && username_Loginid.isNotEmpty()){
                        homeScreenViewModel.updatePassword(
                            newSrc = source,
                            newPass = actualPassword,
                            newLogin = username_Loginid,
                            id = password.id
                        )
                        Toast.makeText(context,"Password updated successfully" , Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                    else{
                        Toast.makeText(context,"Above field(s) cannot be empty" , Toast.LENGTH_SHORT).show()
                    }

                }
            ) {
                Text(
                    text = "UPDATE",
                    fontWeight = Bold
                )
            }
        }
    }
}