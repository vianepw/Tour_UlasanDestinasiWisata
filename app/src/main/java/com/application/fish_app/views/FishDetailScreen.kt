package com.application.fish_app.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.fish_app.R
import com.application.fish_app.data.database.Fish
import com.application.fish_app.data.viewmodel.FishViewModel
import com.application.fish_app.navigation.Screen
import com.application.fish_app.views.utils.CustomUpdateButton
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishDetailScreen(id: Int, fishViewModel: FishViewModel, navController: NavController) {
    val context = LocalContext.current
    var fishNameState: String? by remember { mutableStateOf(null) }
    var amountState: String? by remember { mutableStateOf(null) }
    var fishColorState: String? by remember { mutableStateOf(null) }
    var priceState: String? by remember { mutableStateOf(null) }
    var isFishColorDropDownExpanded by remember { mutableStateOf(false) }
    var isPriceDropDownExpanded by remember { mutableStateOf(false) }
    val fishColorList = listOf("Red", "Blue", "Green", "Yellow", "White", "Black", "Orange", "Brown", "Silver", "Gold",)
    val priceList = listOf(
        "Rp. 50,000 ",
        "Rp. 100,000",
        "Rp. 150,000",
        "Rp. 200,000",
        "Rp. 250,000",
        "Rp. 300,000",
        "Rp. 350,000",
        "Rp. 400.000"
    )

    LaunchedEffect(Unit) {
        fishViewModel.getFish(id)
    }
    fishViewModel.getFish(id)

    val fish = fishViewModel.getFish.observeAsState().value
    fish ?: return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Details Fish",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 60.dp),
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                OutlinedTextField(
                    value = fishNameState
                        ?: fish.fishName,  // display database text if no modified text available
                    onValueChange = { fishNameState = it },
                    label = { Text(stringResource(id = R.string.fish_name)) },
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = amountState
                        ?: fish.amount,
                    onValueChange = { amountState = it },
                    label = { Text(stringResource(id = R.string.amount)) },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box {
                    OutlinedTextField(
                        value = fishColorState ?: fish.fishColor,
                        onValueChange = { fishColorState = it },
                        placeholder = { androidx.compose.material.Text(text = fish.fishColor) },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isFishColorDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowCircleDown, "") }
                    )

                    DropdownMenu(
                        expanded = isFishColorDropDownExpanded,
                        onDismissRequest = { isFishColorDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        fishColorList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                fishColorState = selectedItem
                                isFishColorDropDownExpanded = false
                            }) {
                                androidx.compose.material.Text(selectedItem)
                            }
                            if (index != fishColorList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {
                    OutlinedTextField(
                        value = priceState ?: fish.price,
                        onValueChange = { priceState = it },
                        placeholder = { androidx.compose.material.Text(text = fish.price) },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isPriceDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowCircleDown, "") }
                    )

                    DropdownMenu(
                        expanded = isPriceDropDownExpanded,
                        onDismissRequest = { isPriceDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        priceList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                priceState = selectedItem
                                isPriceDropDownExpanded = false
                            }) {
                                androidx.compose.material.Text(selectedItem)
                            }
                            if (index != priceList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                CustomUpdateButton(stringResource(id = R.string.update_fish))
                {
                    // Create the Fish object
                    val fish = Fish(
                        fishName = fishNameState ?: fish.fishName,
                        amount = amountState ?: fish.amount,
                        fishColor = fishColorState ?: fish.fishColor,
                        price = priceState ?: fish.price
                    )

                    // Update the Fish in the database
                    fishViewModel.updateFish(
                        id,
                        fish.fishName,
                        fish.amount,
                        fish.fishColor,
                        fish.price
                    )
                    Toast.makeText(context, "Fish updated successfully", Toast.LENGTH_SHORT)
                        .show()

                }
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    val openDialog = remember { mutableStateOf(false) }

                    Button(onClick = { openDialog.value = true }) {
                        Text(text = "Delete Fish")
                    }

                    if (openDialog.value) {
                        AlertDialog(
                            onDismissRequest = { openDialog.value = false },
                            title = {
                                Text(text = "Deleting Fish")
                            },
                            text = {
                                Text(text = "Do you really want to Delete this Fish ?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        fish?.let { id ->
                                            fishViewModel.deleteFish(id)
                                        }
                                        openDialog.value = false
                                        Toast.makeText(context, "Fish Deleted successfully", Toast.LENGTH_SHORT)
                                            .show()
                                        navController.navigate(Screen.AllFishesScreen.route)
                                    },
                                ) {
                                    Text(text = "CONFIRM")

                                }
                            },
                            dismissButton = {
                                Button(onClick = { openDialog.value = false }
                                ) {
                                    Text(text = "CANCEL")
                                }
                            }
                        )
                    }
                }

            }

        }

    }
}



