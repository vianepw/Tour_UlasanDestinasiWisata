package com.application.tour_destinasi.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("main_screen")
    object AddFishScreen : Screen("add_a_destinasi")
    object FishDetailsScreen : Screen("fish_details")
    object AllFishesScreen : Screen("all_fishes")
}
