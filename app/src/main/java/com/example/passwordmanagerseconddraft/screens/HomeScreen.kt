package com.example.passwordmanagerseconddraft.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.passwordmanagerseconddraft.R
import com.example.passwordmanagerseconddraft.Screens
import com.example.passwordmanagerseconddraft.auth.AuthViewModel
import com.example.passwordmanagerseconddraft.db.EachPassword
import kotlinx.coroutines.delay

lateinit var setUpdateScreens : EachPassword



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    authViewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val allPasswords by homeScreenViewModel.getAllPasswords().observeAsState()

    var isDeletAccountAlertDialogVisible by remember {
        mutableStateOf(false)
    }

    var isAccountDialogVisisble by remember {
        mutableStateOf(false)
    }

    var isSignoutAlertDialogVisible by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    if (isDeletAccountAlertDialogVisible){
        Box{
            AlertDialog(
                onDismissRequest = { isDeletAccountAlertDialogVisible = false },
                title = {
                    Text(text = "Are you sure to delete your account ? ")
                },
                confirmButton = {
                    Button(onClick = {
                        homeScreenViewModel.deleteAllRecordsForAnAccount(
                            currentUserId = authViewModel.auth.currentUser?.email.toString()
                        )
                        authViewModel.deleteAcc(context)
                        navController.navigate(Screens.Loading.name)
                    }) {
                        Text(text = "Yes")
                    }

                },
                dismissButton = {
                    OutlinedButton(onClick = {
                        isDeletAccountAlertDialogVisible = false
                    }) {
                        Text(text = "Cancel")
                    }
                }

            )
        }

    }

    if (isSignoutAlertDialogVisible){
        Box{
            AlertDialog(
                onDismissRequest = { isSignoutAlertDialogVisible = false },
                title = {
                    Text(text = "Are you sure to Signout ? ")
                },
                confirmButton = {
                    Button(onClick = {
                        authViewModel.signOut()
                        Toast.makeText(
                            context,
                            "Signing out",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Screens.Loading.name)
                    }) {
                        Text(text = "Yes")
                    }

                },
                dismissButton = {
                    OutlinedButton(onClick = {
                        isSignoutAlertDialogVisible = false
                    }) {
                        Text(text = "Cancel")
                    }
                }

            )
        }

    }

    if (isAccountDialogVisisble){
        AlertDialog(
            onDismissRequest = {isAccountDialogVisisble = false},
            confirmButton = {
                Button(
                    onClick = { isAccountDialogVisisble = false }
                ) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Column {
                    TextButton(onClick = { isSignoutAlertDialogVisible = true }) {
                        Text(text = "Signout")
                    }
                    TextButton(onClick = { isDeletAccountAlertDialogVisible = true }) {
                        Text(text = "Delete Account")
                    }
                }
            },
        )
    }


    LaunchedEffect(key1 = authViewModel.appAuthState) {
        when(authViewModel.appAuthState){
            is AuthViewModel.AppAuthState.unauthenticated->navController.navigate(Screens.Login.name)
            is AuthViewModel.AppAuthState.Loading -> navController.navigate(Screens.Loading.name)
            else ->Unit
        }

    }

    Scaffold(
        topBar = {
            AppHeading(
                accountIconClicked = {
                    isAccountDialogVisisble = true
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.Add.name)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
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
            Box{
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeading(
    accountIconClicked:()->Unit,
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
                    onClick = accountIconClicked
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

@Composable
fun EachPasswordCard(
    password: EachPassword,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,

    modifier: Modifier = Modifier
) {
    var isShowPass by remember {
        mutableStateOf(false)
    }



    if (isShowPass){
        AlertDialog(
            onDismissRequest = {
                isShowPass = false
            },
            title = {
                Text(text = "Your password is : ${password.password}")
            },
            confirmButton = {
                LaunchedEffect(key1 = isShowPass) {
                    delay(500)
                    isShowPass = false
                }


            }
        )
    }


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
                    text = "**********",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(25.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
                    
            ){

                IconButton(
                    onClick = {
                        homeScreenViewModel.deletePassword(id = password.id)
                    }
                ) {
                    Icon(
                        Icons.Default.Delete,
                        null,
                        tint = Color.Black,
                        modifier = modifier
                            .border(2.dp, Color.Black, CircleShape)
                            .padding(5.dp)
                    )
                }

                IconButton(
                    onClick = {
                        setUpdateScreens = password
                        navController.navigate(Screens.Update.name)
                    }
                ) {
                    Icon(
                        Icons.Default.Edit,
                        null,
                        tint = Color.Black,
                        modifier = modifier
                            .border(2.dp, Color.Black, CircleShape)
                            .padding(5.dp)
                    )
                }
                IconButton(
                    onClick = {
                        isShowPass = true
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                        null,
                        tint = Color.Black,
                        modifier = modifier
                            .border(2.dp, Color.Black, CircleShape)
                            .padding(5.dp)
                    )
                }
//                OutlinedButton(
//                    onClick = {
//                        homeScreenViewModel.deletePassword(id = password.id)
//                    }
//                ) {
//                    Text(
//                        text = "Delete\npassword",
//                        fontSize = 16.sp
//                    )
//                }
//
//                OutlinedButton(
//                    onClick = {
//                        setUpdateScreens = password
//                        navController.navigate(Screens.Update.name)
//                    }
//                ) {
//                    Text(
//                        text = "Modify",
//                        fontSize = 16.sp
//
//                    )
//                }
//
//                OutlinedButton(
//                    onClick = {
//                        setUpdateScreens = password
//                        navController.navigate(Screens.Update.name)
//                    }
//                ) {
//                    Text(
//                        text = "View\nPassword",
//                        fontSize = 16.sp
//                    )
//
//                }
            }

        }
    }
}