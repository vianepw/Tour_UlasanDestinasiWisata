package com.application.tour_destinasi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.tour_destinasi.navigation.Screen
import com.application.tour_destinasi.ui.theme.BlueLight
import com.application.tour_destinasi.views.utils.CustomButtonWhite


@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().background(color = BlueLight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Tour Destination",
                fontFamily = FontFamily.Monospace,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Mencari Tempat Tujuan Wisata Di Sini",
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(100.dp))
            CustomButtonWhite("List all Destination") { navController.navigate(route = Screen.AllFishesScreen.route) }
            Spacer(modifier = Modifier.height(20.dp))
            CustomButtonWhite("Add a Destination") { navController.navigate(Screen.AddFishScreen.route) }
        }
    }
}