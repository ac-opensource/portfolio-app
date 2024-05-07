package com.aconcepcion.portfolio.ui.home.aboutme

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aconcepcion.portfolio.R
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme

@Composable
fun AboutMe() {
    val scrollState = rememberScrollState()

    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp),
        ) {

            Box {
                Image(
                    painter = painterResource(id = R.drawable.img_profile_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .then(Modifier.graphicsLayer {
                            alpha = 0.99f
                        })
                )

                Canvas(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    val wavePath = Path().apply {
                        moveTo(0f, size.height * 0.85f)

                        cubicTo(
                            x1 = size.width * 0.1f,
                            y1 = size.height * 1.2f,
                            x2 = size.width * 0.9f,
                            y2 = size.height * 0.3f,
                            x3 = size.width,
                            y3 = size.height * 0.8f
                        )

                        lineTo(size.width, size.height + 100)
                        lineTo(0f, size.height + 100)
                        close()
                    }
                    clipPath(
                        path = wavePath,
                        clipOp = androidx.compose.ui.graphics.ClipOp.Intersect
                    ) {
                        drawRect(
                            color = Color.White,
                            size = this.size
                        )
                    }
                }

                ContactMe(Modifier.align(Alignment.BottomEnd))
            }


            Text(
                text = "A-Ar Andrew V. Concepcion",
                style = MaterialTheme.typography.displaySmall,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .offset(y = (-16).dp)
            )

            Text(
                text = "Mobile Developer",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .offset(y = (-16).dp)
            )

            Text(
                text =
                """
                    Dynamic and seasoned software engineer with extensive experience in developing, optimizing, and maintaining Android and iOS applications using Kotlin and Swift. Adept at implementing robust, scalable solutions across various domains including ecommerce, financial services, transportation, and digital advertising. Proven ability to adapt and thrive in fast-paced environments, demonstrating leadership in guiding cross-functional teams toward achieving project milestones. With a profound understanding of modern app architecture and a commitment to continuous learning, I am poised to harness my experience to deliver high-quality and performant applications.
                """.trimIndent(),
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.size(16.dp))

            ProfileReport()

        }
    }


}

@Composable
fun ContactMe(modifier: Modifier) {
    val context = LocalContext.current
    val buttonBackgroundColor = Color(0x88FAD390)
    val buttonContentColor = Color(0xFF333333)

    Row(modifier = modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp)) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:<removed>")
                }
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
                contentColor = buttonContentColor
            )
        ) {
            Icon(Icons.Filled.Call, contentDescription = "Call")
        }

        Spacer(modifier = Modifier.size(4.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:<removed>")
                }
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
                contentColor = buttonContentColor
            )
        ) {
            Icon(Icons.Filled.Email, contentDescription = "Email", modifier = Modifier.size(24.dp))
        }

        Spacer(modifier = Modifier.size(4.dp))

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://github.com/ac-opensource")
                }
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
                contentColor = buttonContentColor
            )
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_github_logo), contentDescription = "GitHub", modifier = Modifier.size(24.dp))
        }
    }
}


@Composable
fun ProfileReport() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "EMERGENETICS REPORT",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "HOW YOU THINK: PERCENTAGES",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            FullDonutChart(
                data = listOf(
                    "Structural" to Color.Green,
                    "Social" to Color.Red,
                    "Conceptual" to Color(0xFFFFD700),
                    "Analytical" to Color.Blue,
                ),
                values = listOf(20f, 15f, 32f, 33f),
                size = 200.dp
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top descriptions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TraitDescription(
                        title = "ANALYTICAL = 33%",
                        description = listOf(
                            "Clear thinker",
                            "Logical problem solver",
                            "Data driven",
                            "Rational",
                            "Learns by mental analysis"
                        ),
                        alignment = Alignment.Start,
                        color = Color.Blue,
                        )
                    TraitDescription(
                        title = "STRUCTURAL = 20%",
                        description = listOf(
                            "Practical thinker",
                            "Likes guidelines",
                            "Cautious of new ideas",
                            "Predictable",
                            "Learns by doing"
                        ),
                        alignment = Alignment.End,
                        color = Color.Green,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TraitDescription(
                        title = "CONCEPTUAL = 32%",
                        description = listOf(
                            "Imaginative",
                            "Initiates about ideas",
                            "Enjoys the unusual",
                            "Likes experimenting",
                            "Learns by imagining"
                        ),
                        alignment = Alignment.Start,
                        color = Color(0xFFFFD700),
                    )
                    TraitDescription(
                        title = "SOCIAL = 15%",
                        description = listOf(
                            "Relational",
                            "Expressive",
                            "Sympathetic",
                            "Learns from others"
                        ),
                        alignment = Alignment.End,
                        color = Color.Red,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "HOW YOU COMPARE TO THE GENERAL POPULATION",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "How you think: Percentile",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        ComparisonBars()

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "How you behave: Percentile",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        BehaviorPercentile()
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun FullDonutChart(
    data: List<Pair<String, Color>>,
    values: List<Float>,
    size: Dp,
    strokeWidth: Float = 100f
) {
    val total = values.sum()
    val sizePx = with(LocalDensity.current) { size.toPx() }
    var startAngle = 0f
    val arcSize = sizePx - strokeWidth

    Canvas(modifier = Modifier
        .size(size)
        .rotate(-70f)) {
        data.zip(values).forEach { (labelColorPair, value) ->
            val sweepAngle = value / total * 360f

            drawArc(
                color = labelColorPair.second,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth),
                size = Size(size.toPx(), size.toPx()),
                topLeft = Offset(strokeWidth / 2, strokeWidth / 2)
            )

            startAngle += sweepAngle
        }
    }
}


@Composable
fun ComparisonBars() {
    val barData = listOf(
        Triple("Analytical", Color.Blue, 95),
        Triple("Structural", Color.Green, 57),
        Triple("Social", Color.Red, 43),
        Triple("Conceptual", Color(0xFFFFD700), 93),
    )
    Column(Modifier.padding(bottom = 10.dp)) {
        barData.forEach { (label, color, percentage) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.width(100.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .padding(end = 40.dp)
                    ) {
                        drawRoundRect(
                            color = color,
                            size = size.copy(width = size.width * percentage / 100),
                            cornerRadius = CornerRadius(8f)
                        )
                    }

                    Text(
                        text = "$percentage%",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                }

            }
        }
    }
}


@Composable
fun BehaviorPercentile() {
    val behaviorData = listOf(
        Triple(
            "Expressiveness",
            49,
            listOf("Quiet", "Introspective", "Reserved", "Talkative", "Gregarious")
        ),
        Triple(
            "Assertiveness",
            51,
            listOf("Peacekeeping", "Easygoing", "Competitive", "Forceful", "Driving")
        ),
        Triple(
            "Flexibility",
            60,
            listOf("Focused", "Firm", "Adaptable", "Accommodating", "Welcomes Change")
        )
    )
    val labelRotationAngle = 0f

    Column {
        behaviorData.forEach { (mainLabel, percentage, subLabels) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mainLabel,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.width(100.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .padding(end = 40.dp)
                    ) {
                        drawRoundRect(
                            color = Color.Magenta,
                            size = size.copy(width = size.width * percentage / 100),
                            cornerRadius = CornerRadius(8f)
                        )
                    }
                    Text(
                        text = "$percentage%",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 100.dp, end = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                subLabels.forEach { subLabel ->
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = subLabel,
                            modifier = Modifier.rotate(labelRotationAngle),
                            style = TextStyle(fontSize = 8.sp, color = Color.Black)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TraitDescription(
    title: String,
    color: Color,
    description: List<String>,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        modifier = Modifier.width(150.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 14.sp, color = color, fontWeight = FontWeight.Bold)
        )
        description.forEach {
            Text(
                text = it,
                style = TextStyle(fontSize = 12.sp, color = Color.Gray)
            )
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun AboutMePreview() {
    PortfolioTheme {
        AboutMe()
    }
}
