package com.example.passwordmanagerseconddraft.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Text(
                text = "all saved passwords shown here",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp

            )

            allPasswords?.let {
                if (!allPasswords.isNullOrEmpty()){
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        items(allPasswords!!){eachpass->
                            EachPasswordCard(
                                password = eachpass ,
                                homeScreenViewModel,
                                navController,
                            )
                        }
                    }
                }else{
                    Spacer(modifier = Modifier.height(125.dp))
                    Text(
                        text = "no saved passwords currently",
                        fontSize = 60.sp,
                        lineHeight = 60.sp,
                        textAlign = TextAlign.Center
                    )
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
            .padding(12.dp)


    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                modifier = modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = "password source : ",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier

                )
                Text(
                    text = password.source,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,

                modifier = modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = "username/loginId : ",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = password.username_loginId,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,

                modifier = modifier
                    .fillMaxWidth()

            ) {
                Text(
                    text = "password : ",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = modifier

                )
                Text(
                    text = password.password,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(25.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    
            ){
                OutlinedButton(
                    onClick = {
                        homeScreenViewModel.deletePassword(id = password.id)
                    }
                ) {
                    Text(text = "Delete password")
                }

                Spacer(modifier = Modifier.width(25.dp))

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