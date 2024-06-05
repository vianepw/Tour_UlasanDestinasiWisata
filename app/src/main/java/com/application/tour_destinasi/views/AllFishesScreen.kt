package com.application.tour_destinasi.views

import HandleBackPress
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.tour_destinasi.data.database.Fish
import com.application.tour_destinasi.data.viewmodel.FishViewModel
import com.application.tour_destinasi.navigation.Screen
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.application.tour_destinasi.R
import com.application.tour_destinasi.ui.theme.BlueLight
import com.application.tour_destinasi.views.utils.CustomButtonDetail


@Composable
fun AllFishesScreen(navController: NavController, fishViewModel: FishViewModel) {
    val fishes: List<Fish> by fishViewModel.allFishes.observeAsState(initial = listOf())
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(Unit) {
        fishViewModel.getFishes()
    }

    LaunchedEffect(Unit) {
        fishViewModel.getFishes()
    }

    Box(
        modifier = Modifier
            .background(color = BlueLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 0.dp)
                .background(color = BlueLight)
        ) {
            if (fishes.isEmpty()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 120.dp, top = 20.dp)) {
                    Text(
                        text = stringResource(id = R.string.all_fishes_title),
                        modifier = Modifier,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
                Column(
                    modifier = Modifier.offset(y = (0).dp)
                ) {
                    EmptyContent()
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(start = 120.dp, top = 20.dp, bottom = 35.dp)) {
                    Text(
                        text = stringResource(id = R.string.all_fishes_title),
                        modifier = Modifier,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .background(color = BlueLight),
                ) {
                    items(fishes) { fish ->
                        OutlinedCard(
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable {
                                    navController.navigate(route = Screen.FishDetailsScreen.route + "/" + fish.id)
                                }
                                .padding(start = 8.dp, bottom = 12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 0.dp, vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                        model = "https://source.unsplash.com/400x400/?fish+tuna",
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize()
                                        .clip(shape = RoundedCornerShape(10.dp))
                                )
                                Text(
                                    text = fish.fishName,
                                    modifier = Modifier
                                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 26.sp
                                )
                                Text(
                                    text = "Color - ${fish.fishColor}",
                                    modifier = Modifier
                                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Amount - ${fish.amount}",
                                    modifier = Modifier
                                        .padding(0.dp, 3.dp, 0.dp, 0.dp),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.End
                                )
                                Text(
                                    text = "${fish.price}",
                                    modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 8.dp),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                CustomButtonDetail(buttonText = "Detail Fish") {
                                    navController.navigate(Screen.FishDetailsScreen.route + "/" + fish.id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    if (backDispatcher != null) {
        HandleBackPress(backDispatcher) {
            navController.navigate(Screen.HomeScreen.route)
        }
    }
}

@Composable
fun EmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = BlueLight),
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(
                R.drawable.fish
            ), contentDescription = stringResource(
                R.string.no_fish
            ),
            tint = Color.LightGray
        )
        androidx.compose.material.Text(
            text = stringResource(
                R.string.text_empty_content
            ),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = Color.LightGray,
            modifier = Modifier.padding(top = 14.dp),
        )
    }
}
