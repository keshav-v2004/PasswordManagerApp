package com.example.passwordmanagerseconddraft

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanagerseconddraft.auth.AuthViewModel
import com.example.passwordmanagerseconddraft.auth.LoginPage
import com.example.passwordmanagerseconddraft.auth.SignupPage
import com.example.passwordmanagerseconddraft.screens.AddScreen
import com.example.passwordmanagerseconddraft.screens.HomeScreen
import com.example.passwordmanagerseconddraft.screens.HomeScreenViewModel
import com.example.passwordmanagerseconddraft.screens.UpdateScreen
import com.example.passwordmanagerseconddraft.screens.setUpdateScreens

enum class Screens{
    Login,
    Signup,
    Home,
    Add,
    Update
}



@Composable
fun MasterApp(
    homeScreenViewModel: HomeScreenViewModel,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val navController : NavHostController = rememberNavController()


    NavHost(
        navController = navController, startDestination = Screens.Login.name
    ) {

        composable(route = Screens.Home.name , content = {
            HomeScreen(
                navController = navController,
                homeScreenViewModel = homeScreenViewModel,
                authViewModel = authViewModel,
            )
        })
        composable(route = Screens.Login.name , content = {
            LoginPage(
                authViewModel = authViewModel,
                navigateToSignupPage = {navController.navigate(Screens.Signup.name)},
                navigateToHomeScreen = {navController.navigate(Screens.Home.name)}
            )
        })
        composable(route = Screens.Signup.name , content = {
            SignupPage(
                authViewModel = authViewModel,
                navigateToLoginPage = {navController.navigate(Screens.Login.name)},
                navigateToHomeScreen = {navController.navigate(Screens.Home.name)}
            )
        })
        composable(route = Screens.Add.name , content = {
            AddScreen(
                homeScreenViewModel,navController
            )
        })

        composable(route = Screens.Update.name , content = {
            UpdateScreen(
                homeScreenViewModel = homeScreenViewModel,
                navController = navController,
                password = setUpdateScreens
            )
        })

    }
}