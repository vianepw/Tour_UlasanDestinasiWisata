package com.application.tour_destinasi.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.application.tour_destinasi.R
import com.application.tour_destinasi.data.database.Fish
import com.application.tour_destinasi.data.viewmodel.FishViewModel
import com.application.tour_destinasi.ui.theme.BlueLight
import com.application.tour_destinasi.views.utils.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFishScreen(fishViewModel: FishViewModel) {
    val context = LocalContext.current
    var fishName by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var fishColor by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var isFishColorDropDownExpanded by remember { mutableStateOf(false) }
    var isPriceDropDownExpanded by remember { mutableStateOf(false) }
    val fishColorList = listOf("Red", "Blue", "Green", "Yellow", "White", "Black", "Orange", "Brown", "Silver", "Gold")
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

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Tambah Destinasi",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = BlueLight,
                modifier = Modifier
                    .padding(top = 60.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = fishName,
                label = { Text(stringResource(id = R.string.fish_name)) },
                onValueChange = {
                    fishName = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = amount,
                label = { Text(stringResource(id = R.string.amount)) },
                onValueChange = {
                    amount = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    OutlinedTextField(
                        value = fishColor,
                        onValueChange = { fishColor = it },
                        placeholder = { Text(text = "Pilih Tempat Destinasi") },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isFishColorDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null) }
                    )

                    DropdownMenu(
                        expanded = isFishColorDropDownExpanded,
                        onDismissRequest = { isFishColorDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                    ) {
                        fishColorList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                fishColor = selectedItem
                                isFishColorDropDownExpanded = false
                            }) {
                                Text(selectedItem)
                            }
                            if (index != fishColorList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        placeholder = { Text(text = "Pilih Harga") },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isPriceDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null) }
                    )

                    DropdownMenu(
                        expanded = isPriceDropDownExpanded,
                        onDismissRequest = { isPriceDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                    ) {
                        priceList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                price = selectedItem
                                isPriceDropDownExpanded = false
                            }) {
                                Text(selectedItem)
                            }
                            if (index != priceList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            CustomButton(stringResource(id = R.string.add_fish)) {
                // Create the Fish object
                if (fishName == "" || amount == "" || fishColor == "" || price == "") {
                    Toast.makeText(context, "Gagal Menambah Destinasi", Toast.LENGTH_SHORT).show()
                    Log.d("data db", "Data Gagal")
                } else {
                    val fish = Fish(fishName, amount, fishColor, price)
                    Log.d("data db", "Data Berhasil $fish")

                    // Update the Fish to the database
                    fishViewModel.addFish(fish)
                    // Clear text fields
                    fishName = ""
                    amount = ""
                    fishColor = ""
                    price = ""
                    Toast.makeText(context, "Berhasil Menambah Destinasi", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
