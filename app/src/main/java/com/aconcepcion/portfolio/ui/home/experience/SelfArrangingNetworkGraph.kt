package com.aconcepcion.portfolio.ui.home.experience

import android.text.TextPaint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.aconcepcion.portfolio.ui.theme.MidnightBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


data class FloatingNode(
    val skill: SkillNode,
    val position: Animatable<Offset, AnimationVector2D>,
    var isDragging: Boolean = false
)

@Composable
fun SelfArrangingNetworkGraph(skills: List<SkillNode>, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val canvasSize = remember { mutableStateOf(Size.Zero) }

    val floatingNodes = remember {
        skills.map { skill ->
            FloatingNode(
                skill = skill,
                position = Animatable(
                    Offset(Random.nextFloat() * maxOf(800f, canvasSize.value.width), Random.nextFloat() * maxOf(1200f, canvasSize.value.height)),
                    Offset.VectorConverter
                )
            )
        }
    }

    LaunchedEffect(floatingNodes) {
        floatingNodes.forEach { node ->
            scope.launch {
                while (true) {
                    val randomX = Random.nextFloat() * canvasSize.value.width
                    val randomY = Random.nextFloat() * canvasSize.value.height
                    val targetPosition = Offset(randomX, randomY)

                    adjustForOverlaps(floatingNodes, scope)

                    node.position.animateTo(
                        targetValue = targetPosition,
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
                    )

                    if (!node.isDragging) {
                        delay(7000)
                    }
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = modifier
            .matchParentSize()
            .onGloballyPositioned { layoutCoordinates ->
                canvasSize.value = layoutCoordinates.size.toSize()
            }) {
            floatingNodes.forEach { node ->
                node.skill.connections.forEach { connection ->
                    floatingNodes.find { it.skill.name == connection }?.let { connectedNode ->
                        drawLine(
                            start = node.position.value,
                            end = connectedNode.position.value,
                            color = Color.Gray.copy(alpha = 0.5f),
                            strokeWidth = 2f
                        )
                    }
                }
            }

            val baseNodeRadius = 10f
            val space = 250f
            val origin = Offset(size.width / 2, size.height / 2)

            floatingNodes.forEach { node ->
                val connectionsCount = node.skill.connections.size
                val nodeRadius = baseNodeRadius + ((calculateTotalConnections(node.skill, skills)).toFloat() * 1f)

                val x = node.position.value.x.coerceIn(0f, size.width)
                val y = node.position.value.y.coerceIn(0f, size.height)

                drawCircle(
                    color = MidnightBlue,
                    radius = nodeRadius,
                    center = Offset(x, y)
                )
                val textOffsetX = if (x > origin.x) x + nodeRadius else x - nodeRadius
                val textOffsetY = y - nodeRadius

                drawContext.canvas.nativeCanvas.drawText(
                    node.skill.name,
                    textOffsetX,
                    textOffsetY,
                    TextPaint().apply {
                        textSize = 12.sp.toPx() + connectionsCount * 2
                        color = Color.Black.toArgb()
                        textAlign =
                            if (x > origin.x) android.graphics.Paint.Align.LEFT else android.graphics.Paint.Align.RIGHT
                    }
                )
            }
        }

    }
}

fun calculateTotalConnections(node: SkillNode, skills: List<SkillNode>, visited: MutableSet<String> = mutableSetOf()): Int {
    val directConnections = node.connections.size
    if (node.name in visited) return 0
    visited.add(node.name)
    val childConnections = node.connections.sumOf { connectionName ->
        skills.find { it.name == connectionName }?.let { childNode ->
            calculateTotalConnections(childNode, skills, visited)
        } ?: 0
    }

    return directConnections + childConnections
}


suspend fun adjustForOverlaps(
    nodes: List<FloatingNode>,
    scope: CoroutineScope,
    minDistance: Float = 150f
) {
    scope.launch {
        nodes.forEach { node ->
            nodes.filter { it != node }.forEach { other ->
                val delta = node.position.value - other.position.value
                val distance = delta.getDistance()
                if (distance < minDistance) {
                    val adjustment = (delta / distance) * (minDistance - distance) / 2f
                    node.position.animateTo(node.position.value + adjustment, spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow))
                    other.position.animateTo(other.position.value - adjustment, spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow))
                }
            }
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