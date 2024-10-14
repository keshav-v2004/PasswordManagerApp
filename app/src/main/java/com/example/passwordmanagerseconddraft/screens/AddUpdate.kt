package com.example.passwordmanagerseconddraft.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.passwordmanagerseconddraft.db.EachPassword

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
            AppHeading()
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
                }
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
                }
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
                }
            )

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    if (source.isNotEmpty() && username_Loginid.isNotEmpty() && password.isNotEmpty()){
                        homeScreenViewModel.insertNewPassword(
                            EachPassword(
                                source = source,
                                username_loginId = username_Loginid,
                                password = password
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

    Scaffold(
        topBar = {
            AppHeading()
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
                }
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
                }
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
                }
            )

            Spacer(Modifier.height(30.dp))

            OutlinedButton(
                onClick = {

                    homeScreenViewModel.updatePassword(
                        newSrc = source,
                        newPass = actualPassword,
                        newLogin = username_Loginid,
                        id = password.id
                    )
                    navController.popBackStack()
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