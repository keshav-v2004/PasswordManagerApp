package com.example.passwordmanagerseconddraft.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.passwordmanagerseconddraft.Screens
import com.example.passwordmanagerseconddraft.db.EachPassword

lateinit var setUpdateScreens : EachPassword

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val allPasswords by homeScreenViewModel.getAllPasswords().observeAsState()

    Scaffold(
        topBar = {
            AppHeading()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.Add.name)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(it)
                .statusBarsPadding()
                .fillMaxSize()

        ) {
            Text(text = "all saved passwords shown here")

            allPasswords?.let {
                if (!allPasswords.isNullOrEmpty()){
                    LazyColumn() {
                        items(allPasswords!!){eachpass->
                            EachPasswordCard(
                                password = eachpass ,
                                homeScreenViewModel,
                                navController
                            )
                        }
                    }
                }else{
                    Text(text = "no saved passwords")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeading(
    modifier: Modifier = Modifier
) {
    MediumTopAppBar(
        title = {
            Text(
                text = "Password Manager",
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarColors(
            containerColor = Color.Magenta,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        )
    )
}

@Composable
fun EachPasswordCard(
    password: EachPassword,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,

    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier


    ) {
        Column(
            modifier = modifier

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier

            ) {
                Text(text = "password source : ")
                Text(text = password.source)

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier

            ) {
                Text(text = "username/loginId : ")
                Text(text = password.username_loginId)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier

            ) {
                Text(text = "password : ")
                Text(text = password.password)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ){
                OutlinedButton(
                    onClick = {
                        homeScreenViewModel.deletePassword(id = password.id)
                    }
                ) {
                    Text(text = "Delete password")
                }

                OutlinedButton(
                    onClick = {
                        setUpdateScreens = password
                        navController.navigate(Screens.Update.name)
                    }
                ) {
                    Text(text = "Modify")
                }
            }

        }
    }
}