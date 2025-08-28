package com.example.borutoapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.presentation.screen.splash.SplashScreen
import com.example.borutoapp.presentation.screen.welcome.WelcomeScreen
import com.example.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Home.route) {}
        composable(
            Screen.Details.route,
            listOf(
                navArgument(DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                },
            ),
        ) {}
        composable(Screen.Search.route) {}
    }
}
