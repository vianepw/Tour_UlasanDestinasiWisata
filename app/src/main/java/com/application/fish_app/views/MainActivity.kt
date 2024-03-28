package com.application.fish_app.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.application.fish_app.data.viewmodel.FishViewModel
import com.application.fish_app.navigation.Screen
import com.application.fish_app.ui.theme.FishAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            FishAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val fishViewModel = FishViewModel(application)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.AddFishScreen.route) {
                            AddFishScreen(fishViewModel = fishViewModel)
                        }
                        composable(route = Screen.AllFishesScreen.route) {
                            AllFishesScreen(
                                navController = navController,
                                fishViewModel = fishViewModel
                            )
                        }
                        composable(
                            route = Screen.FishDetailsScreen.route + "/{fish_id}",
                            arguments = listOf(
                                navArgument("fish_id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getInt("fish_id") ?: -1
                            FishDetailScreen(id, fishViewModel = fishViewModel, navController)
                        }
                    }
                }
            }
        }
    }
}