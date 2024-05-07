package com.aconcepcion.portfolio.ui.home.skills

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.aconcepcion.portfolio.ui.home.experience.SelfArrangingNetworkGraph
import com.aconcepcion.portfolio.ui.home.experience.skills
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Skills() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val showTooltip = remember { mutableStateOf(false) }
    val tooltipText = remember { mutableStateOf("") }
    val tooltipOffset = remember { mutableStateOf(Offset(0f, 0f)) }
    val tooltipOpacity = remember { Animatable(1f) }
    val fadeOutJob = remember { mutableStateOf<Job?>(null) }

    if (showTooltip.value) {
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(tooltipOffset.value.x.toInt(), tooltipOffset.value.y.toInt()),
            properties = PopupProperties()
        ) {
            Card(elevation = CardDefaults.elevatedCardElevation(4.dp),
                modifier = Modifier.graphicsLayer {
                    alpha = tooltipOpacity.value
                }) {
                Text(
                    text = tooltipText.value,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .padding(bottom = 80.dp)

    ) {

        Text(
            text = "Skills",
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.displayMedium,
        )

        val skillsList = listOf(
            "Android" to "10 years of experience",
            "Android SDK" to "10 years of experience",
            "Clean Architecture" to "7 years of experience",
            "Jetpack Compose" to "2 years of experience",
            "Kotlin" to "7 years of experience",
            "MVP" to "3 years of experience",
            "MVVM" to "4 years of experience",
            "Bluetooth" to "3 years of experience",
            "NFC" to "4 years of experience",
            "Permissions" to "10 years of experience",
            "SQLite" to "10 years of experience",
            "Room" to "2 years of experience",
            "LiveData" to "4 years of experience",
            "Coroutines" to "3 years of experience",
            "iOS" to "3 years of experience",
            "Objective-C" to "1 year of experience",
            "Swift" to "3 years of experience",
            "Swift UI" to "1 year of experience",
            "Core Animation" to "1 year of experience",
            "Core Location" to "1 year of experience",
            "Combine" to "3 months of experience",
            "Realm" to "2 years of experience",
            "Flutter" to "1 year of experience",
            "React Native" to "1 year of experience",
            "Backend" to "4 years of experience",
            "Java" to "10 years of experience",
            "Javascript" to "3 years of experience",
            "NodeJS" to "3 years of experience",
            "React" to "1 year of experience",
            "HTML" to "2 years of experience",
            "CSS" to "2 years of experience",
            "Git" to "10 years of experience",
            "CI/CD" to "2 years of experience",
            "Internationalization" to "4 years of experience",
            "Localization" to "5 years of experience",
            "Frameworks" to "3 years of experience",
            "Material Design" to "7 years of experience",
            "Automated Testing" to "2 years of experiencex"
        )

        SkillChips(skills = skillsList) { skill, x, y ->
            coroutineScope.launch {
                fadeOutJob.value?.cancelAndJoin()
                fadeOutJob.value = launch {
                    tooltipText.value = skill
                    tooltipOffset.value = Offset(x, y)
                    showTooltip.value = true
                    tooltipOpacity.snapTo(1f)

                    delay(2000)
                    tooltipOpacity.animateTo(0f, animationSpec = tween(durationMillis = 1000))
                    delay(1000)
                    showTooltip.value = false
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(32.dp)
        ) {
//            NetworkGraph(skills)
            SelfArrangingNetworkGraph(skills = skills)
        }

        Spacer(Modifier.padding(16.dp))


    }


}


@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun SkillsPreview() {
    PortfolioTheme {
        Skills()
    }
}

