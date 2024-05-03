package com.aconcepcion.portfolio.ui.home.experience

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aconcepcion.portfolio.ui.components.TypingAnimationText
import com.aconcepcion.portfolio.ui.home.skills.SkillChips
import com.aconcepcion.portfolio.ui.home.skills.skills
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme
import kotlinx.coroutines.launch

@Composable
fun Skills() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    val scrollDistanceDp = 1250.dp
    val scrollDistancePx = with(LocalDensity.current) { scrollDistanceDp.roundToPx() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .padding(bottom = 80.dp) // 102dp

    ) {
        Text(
            text = "Skills",
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.displayMedium,
        )

        val skillsList = listOf(
            "Android", "Android SDK", "Views", "Jetpack Compose", "Kotlin", "MVP", "MVVM",
            "Bluetooth", "NFC", "Permissions", "SQLite", "Room", "LiveData", "Coroutines", "iOS",
            "Objective-C", "Swift", "Swift UI", "Core Animation", "Core Location", "Combine", "Realm",
            "Flutter", "React Native", "Clean Architecture", "Backend", "Java", "Javascript",
            "NodeJS", "React", "HTML", "CSS", "Git", "CI/CD", "Internationalization", "Localization",
            "Frameworks", "Material Design", "Automated Testing"
        )

        SkillChips(skills = skillsList)

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
