package com.example.borutoapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.borutoapp.presentation.screen.home.HomeScreen
import com.example.borutoapp.presentation.screen.search.SearchScreen
import com.example.borutoapp.presentation.screen.splash.SplashScreen
import com.example.borutoapp.presentation.screen.welcome.WelcomeScreen
import com.example.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalCoilApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            Screen.Details.route,
            listOf(
                navArgument(DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                },
            ),
        ) {}
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}
