package com.aconcepcion.portfolio.ui.home.skills

import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aconcepcion.portfolio.ui.home.experience.calculateTotalConnections
import com.aconcepcion.portfolio.ui.theme.MidnightBlue
import kotlinx.coroutines.delay
import kotlin.math.sqrt
import kotlin.random.Random

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillChips(skills: List<String>) {
    FlowRow(
        verticalArrangement = Arrangement.Top
    ) {
        skills.forEach { skill ->
            AssistChip(
                onClick = { /* Handle click here */ },
                shape = RoundedCornerShape(8.dp),
                colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.padding(4.dp),
                label = {
                    Text(
                        text = skill,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            )
        }
    }
}

data class SkillNode(
    val name: String,
    val connections: List<String>
)

val skills = listOf(
    SkillNode(
        "Android", listOf(
            "Kotlin",
            "Android SDK",
            "Views",
            "Jetpack Compose",
            "MVP",
            "MVVM",
            "Bluetooth",
            "NFC",
            "Permissions",
            "SQLite",
            "Room",
            "LiveData",
            "Coroutines",
        )
    ),
    SkillNode("Kotlin", listOf("Coroutines", "Jetpack Compose")),
    SkillNode("Android SDK", listOf("Permissions")),
    SkillNode("Views", listOf()),
    SkillNode("Jetpack Compose", listOf("Kotlin", "Material Design")),
    SkillNode("MVP", listOf()),
    SkillNode("MVVM", listOf("LiveData")),
    SkillNode("Bluetooth", listOf()),
    SkillNode("NFC", listOf()),
    SkillNode("Permissions", listOf("Android SDK")),
    SkillNode("SQLite", listOf("Room")),
    SkillNode("Room", listOf("SQLite")),
    SkillNode("LiveData", listOf("MVVM")),
    SkillNode("Coroutines", listOf("Kotlin")),
    SkillNode("Mobile", listOf("Android", "iOS")),
    SkillNode(
        "iOS", listOf(
            "Objective-C",
            "Swift",
            "Swift UI",
            "Core Animation",
            "Core Location",
            "Combine",
        )
    ),
    SkillNode("Objective-C", listOf()),
    SkillNode("Swift", listOf("Swift UI", "Combine")),
    SkillNode("Swift UI", listOf("Swift", )),
    SkillNode("Core Animation", listOf()),
    SkillNode("Core Location", listOf()),
    SkillNode("Combine", listOf("Swift"))
)