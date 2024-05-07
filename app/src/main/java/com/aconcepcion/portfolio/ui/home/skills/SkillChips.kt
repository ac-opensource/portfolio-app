package com.aconcepcion.portfolio.ui.home.skills

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillChips(skills: List<Pair<String, String>>, onClick: (String, Float, Float) -> Unit) {
    val density = LocalDensity.current

    FlowRow(
        verticalArrangement = Arrangement.Top
    ) {
        skills.forEach { skill ->
            val position = remember { mutableStateOf(Offset(0f, 0f)) }

            AssistChip(
                onClick = {
                    val yOffset = with(density) { (-72).dp.toPx() }
                    onClick(skill.second, position.value.x, position.value.y + yOffset)
                },
                shape = RoundedCornerShape(8.dp),
                colors = AssistChipDefaults.assistChipColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .padding(4.dp)
                    .onGloballyPositioned { coordinates ->
                        val pos = coordinates.positionInRoot()
                        position.value = Offset(pos.x, pos.y)
                    },
                label = {
                    Text(
                        text = skill.first,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            )
        }
    }
}