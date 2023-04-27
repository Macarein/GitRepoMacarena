package com.macarenarodriguezboleto.openeyes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macarenarodriguezboleto.openeyes.HomeScreen
import com.macarenarodriguezboleto.openeyes.features.splashScreen.AnimatedSplashScreen

@Composable
fun SetupNavGraph() {

    val navController  = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(Screens.Splash.route) {
            AnimatedSplashScreen(navController)
        }

        composable(Screens.Home.route) {
            HomeScreen()
        }
    }
}