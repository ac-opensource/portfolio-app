package com.aconcepcion.portfolio.ui.components

import android.text.Layout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
@Composable
fun TypingAnimationText(text: String,
                        modifier: Modifier = Modifier,
                        style: TextStyle = TextStyle.Default,
                        typingSpeed: Long = 80L,
                        lines: Int = 2) {
    val displayedText = remember { mutableStateOf("") }
    val maxChars = text.length

    LaunchedEffect(key1 = text) {
        for (i in 1..maxChars) {
            displayedText.value = text.substring(startIndex = 0, endIndex = i)
            delay(typingSpeed)
        }
    }

    Text(
        text = displayedText.value,
        modifier = modifier,
        maxLines = lines,
        minLines = lines,
        style = style,
        color = Color.Black
    )
}