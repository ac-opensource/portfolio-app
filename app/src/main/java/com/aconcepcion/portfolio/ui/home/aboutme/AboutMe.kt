package com.aconcepcion.portfolio.ui.home.aboutme

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

            ContactMe()

        }
    }


}

@Composable
fun ContactMe() {
    val context = LocalContext.current
    val buttonBackgroundColor = Color(0x88FAD390)
    val buttonContentColor = Color(0xFF333333)

    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
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
            Spacer(modifier = Modifier.size(8.dp))
            Text("Call")
        }

        Spacer(modifier = Modifier.size(16.dp))

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
            Icon(Icons.Filled.Email, contentDescription = "Email")
            Spacer(modifier = Modifier.size(8.dp))
            Text("Email")
        }

        Spacer(modifier = Modifier.size(16.dp))

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
            Icon(Icons.Filled.Star, contentDescription = "GitHub")
            Spacer(modifier = Modifier.size(8.dp))
            Text("GitHub")
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
