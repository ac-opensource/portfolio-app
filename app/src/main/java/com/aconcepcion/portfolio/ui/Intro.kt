package com.aconcepcion.portfolio.ui


import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aconcepcion.portfolio.R
import com.aconcepcion.portfolio.ui.components.TypingAnimationText
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme
import kotlinx.coroutines.delay


@Composable
fun Intro(
    onPortfolioClicked: () -> Unit,
) {
    val currentText = remember { mutableStateOf("Hello,\nI'm Andrew") }
    var texts = listOf("Hello,\nI'm Andrew", "I'm a mobile developer", "I have experience in")
    val isPrimaryList = remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        var index = 0
        while (true) {
            if (index >= texts.size - 1) {
                delay(if (isPrimaryList.value) 3500 else 3000)
                index = 0
                isPrimaryList.value = !isPrimaryList.value
                texts = if (isPrimaryList.value)
                    listOf("Know more ↘", "Hello,\nI'm Andrew", "I'm a mobile developer", "I have experience in")
                else
                    listOf(
                        "Android",
                        "Android SDK",
                        "Views",
                        "Jetpack Compose",
                        "Kotlin",
                        "MVP",
                        "MVVM",
                        "Bluetooth",
                        "NFC",
                        "Permissions",
                        "SQLite",
                        "Room",
                        "LiveData",
                        "Coroutines",
                        "iOS",
                        "Objective-C",
                        "Swift",
                        "Swift UI",
                        "Core Animation",
                        "Core Location",
                        "Combine",
                        "Realm",
                        "Flutter",
                        "React Native",
                        "Clean Architecture",
                        "Backend",
                        "Java",
                        "Javascript",
                        "NodeJS",
                        "React",
                        "HTML",
                        "CSS",
                        "Git",
                        "CI/CD",
                        "Internationalization",
                        "Localization",
                        "Frameworks",
                        "Material Design",
                        "Automated Testing")
            } else {
                delay(if (isPrimaryList.value) 3500 else 180)
                index++
            }
            currentText.value = texts[index]
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        TypingAnimationText(
            text = currentText.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.displayLarge,
            typingSpeed = if (isPrimaryList.value) 80 else 10
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Image(
                painter = painterResource(id = R.drawable.img_avatar_hi),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )

            Button(
                onClick = { onPortfolioClicked() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                ) {
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Know more")
                Icon(Icons.Default.KeyboardArrowRight,
                    null,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun HomePreview() {
    PortfolioTheme {
        Intro() {}
    }
}
