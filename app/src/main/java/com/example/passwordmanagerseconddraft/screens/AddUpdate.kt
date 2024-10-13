package com.example.passwordmanagerseconddraft.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
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
import androidx.navigation.NavController
import com.example.passwordmanagerseconddraft.db.EachPassword
import kotlin.math.log

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
                    Text(text = "enter source")
                }
            )
            OutlinedTextField(
                value = username_Loginid,
                onValueChange = {
                    username_Loginid = it
                },
                placeholder = {
                    Text(text = "enter username/LoginId")
                }
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = "enter password")
                }
            )

            Button(
                onClick = {
                    homeScreenViewModel.insertNewPassword(
                        EachPassword(
                            source = source,
                            username_loginId = username_Loginid,
                            password = password
                        )
                    )
                    navController.popBackStack()

                }
            ) {
                Text(text = "SAVE / UPDATE")
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
                    Text(text = "enter updated source")
                }
            )
            OutlinedTextField(
                value = username_Loginid,
                onValueChange = {
                    username_Loginid = it
                },
                placeholder = {
                    Text(text = "enter updated username/LoginId")
                }
            )
            OutlinedTextField(
                value = actualPassword,
                onValueChange = {
                    actualPassword = it
                },
                placeholder = {
                    Text(text = "enter updated password")
                }
            )

            Button(
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
                Text(text = "UPDATE")
            }
        }
    }
}