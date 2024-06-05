package com.application.tour_destinasi.views.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.tour_destinasi.ui.theme.BlueDark
import com.application.tour_destinasi.ui.theme.BlueLight

@Composable
fun CustomButton(buttonText: String, onclick: () -> Unit) {
    Button(onClick = onclick) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

@Composable
fun CustomButtonWhite(buttonText: String, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = BlueLight
        )
    ) {
        Text(
            text = buttonText,
            color = BlueLight
        )
    }
}


@Composable
fun CustomButtonDetail(buttonText: String, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueDark,
            contentColor = Color.White
        )
    ) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}


@Composable
fun CustomUpdateButton(buttonText: String, onclick: () -> Unit) {
    Button(onClick = onclick) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

@Composable
fun TitleText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp),
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}
