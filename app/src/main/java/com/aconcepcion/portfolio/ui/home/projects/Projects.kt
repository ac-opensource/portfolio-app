package com.aconcepcion.portfolio.ui.home.projects

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.aconcepcion.portfolio.R
import com.aconcepcion.portfolio.ui.theme.MidnightBlue
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme
import kotlinx.coroutines.launch

data class Project(
    val name: String,
    val desc: String,
    val icon: Int,
    val image: Int,
    val techUsed: List<String>,
    val challenges: List<String>,
    val downloads: String,
    val contributions: List<String>
)

val projects = listOf(
    Project(
        name = "OCBC",
        desc = "Mobile banking and corporate finance management app for one of Singapore's largest banks.",
        icon = R.drawable.ic_ocbc_logo,
        image = R.drawable.img_ocbc_app,
        techUsed = listOf("Kotlin", "XML", "Firebase"),
        challenges = listOf(
            "Charts customization",
            "Identity verification",
            "Handling concurrency",
            "Optimizing network calls"
        ),
        downloads = "1m+",
        contributions = listOf(
            "Third-party SDKs integration",
            "Code refactoring",
            "UI overhaul",
            "Unit testing"
        )
    ),
    Project(
        name = "Solo",
        desc = "Whitelabel food delivery apps and platform including clients like Burger King, Shawarma Classic, Wayback Burgers, etc.",
        icon = R.drawable.ic_solo_logo,
        image = R.drawable.img_solo_app,
        techUsed = listOf("Kotlin", "XML Layouts", "Retrofit"),
        challenges = listOf("Legacy code integration", "API design"),
        downloads = "1m+",
        contributions = listOf("Bug fixing", "New feature implementation")
    ),
    Project(
        name = "MySTC",
        desc = "App for Saudi Telecommunication Company (STC)'s consumer facing services, manager accounts and connections, order devices, get support.",
        icon = R.drawable.ic_stc_logo,
        image = R.drawable.img_mystc_app,
        techUsed = listOf("Kotlin", "Java", "MVVM", "Firebase", "SQLite"),
        challenges = listOf("Data synchronization", "Multi-language support"),
        downloads = "10m+",
        contributions = listOf(
            "Lead a team of 5 senior android developers",
            "Feature development/enhancements",
            "Performance improvements"
        )
    ),
    Project(
        name = "OWTO",
        desc = "A ride-hailing app for the Philippine market.",
        icon = R.drawable.ic_owto_logo,
        image = R.drawable.img_owto_app,
        techUsed = listOf(
            "Kotlin",
            "Swift",
            "Firebase",
            "Uber RIB Architecture",
            "Node.js",
            "Postgres"
        ),
        challenges = listOf("Real-time GPS tracking", "Payment integration"),
        downloads = "500k+",
        contributions = listOf(
            "Lead the whole development team",
            "Android/iOS apps development",
            "Booking algorithm innovations",
            "Backend API development"
        )
    ),
    Project(
        name = "Popslide",
        desc = "Cashback/rewards app in exchange for viewing ads and doing tasks, including changing android's lockscreen into full page ads and articles.",
        icon = R.drawable.ic_popslide_logo,
        image = R.drawable.img_popslide_app,
        techUsed = listOf("Kotlin", "Lockscreen", "Notifications", "Flux", "Firebase"),
        challenges = listOf("User engagement optimization", "Ad delivery optimization"),
        downloads = "1m+",
        contributions = listOf("A/B Testing", "Feature roll-outs", "Refactoring")
    ),
    Project(
        name = "WebSafety",
        desc = "Mobile device software solutions that allow parents to monitor and control what their children access through their smartphones or mobile devices.",
        icon = R.drawable.ic_websafety_logo,
        image = R.drawable.img_websafety_app,
        techUsed = listOf("Java", "Objective-C", "AWS S3", "ElasticSearch"),
        challenges = listOf(
            "Usage of undocumented android APIs",
            "GPS Tracking",
            "Data syncing",
            "Image uploads",
            "Security compliance",
            "Data privacy",
            "Finding workarounds to device limitations"
        ),
        downloads = "50k+",
        contributions = listOf(
            "Discovering undocumented API exploits",
            "Security features implementation"
        )
    ),
    Project(
        name = "ProjectBASS",
        desc = "Bandwidth and Signal Strength is a mobile app designed by volunteers for volunteers to crowdsource location-based bandwidth and signal strength measurements of their carrier of choice.",
        icon = R.drawable.ic_projectbass_logo,
        image = R.drawable.img_projectbass_app,
        techUsed = listOf("Kotlin", "Retrofit"),
        challenges = listOf("Data aggregation", "Carrier compatibility"),
        downloads = "10k+",
        contributions = listOf("Open-source contributions", "Community support")
    )
)

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun Projects() {
    val showSheet = remember { mutableStateOf(false) }
    val selectedProject = remember { mutableStateOf<Project?>(null) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { projects.size })
    val scrollState = rememberScrollState()
    val scrollDistancePx = with(LocalDensity.current) { 88.dp.roundToPx() }
    val currentProject = projects[pagerState.currentPage]

    if (showSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showSheet.value = false },
            sheetState = bottomSheetState,
            modifier = Modifier.padding(16.dp)
        ) {
            val project = currentProject
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_download),
                        contentDescription = "Downloads",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${project.downloads} downloads",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Tech Used:", style = MaterialTheme.typography.bodyLarge)
                FlowRow(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    project.techUsed.forEach { tech ->
                        AssistChip(
                            onClick = {},
                            label = { Text(tech, style = MaterialTheme.typography.bodySmall) },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Challenges:", style = MaterialTheme.typography.bodyLarge)
                project.challenges.forEach { challenge ->
                    Text(
                        "- $challenge",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Contributions:", style = MaterialTheme.typography.bodyMedium)
                project.contributions.forEach { contribution ->
                    Text(
                        "- $contribution",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        Popup(
            alignment = Alignment.TopStart,
            properties = PopupProperties()
        ) {
            ProjectIconAndDescription(
                currentProject = currentProject,
                scrollState = scrollState,
                pagerState = pagerState,
                selectedProject = selectedProject,
                showSheet = showSheet,
                textColor = Color.White
            )
        }
    }


    Surface(color = Color.Transparent) {
        Column {

            LaunchedEffect(key1 = pagerState.currentPage) {
                coroutineScope.launch {
                    scrollState.animateScrollTo(pagerState.currentPage * scrollDistancePx)
                }
            }

            ProjectIconAndDescription(
                currentProject = currentProject,
                scrollState = scrollState,
                pagerState = pagerState,
                selectedProject = selectedProject,
                showSheet = showSheet
            )

            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
                Box {
                    val imageSize = remember { mutableStateOf(IntSize.Zero) }

                    when (currentProject.name.lowercase()) {
                        "solo" -> {
                            Image(
                                painter = painterResource(id = R.drawable.img_solo_app),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(vertical = 32.dp)
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }

                        "popslide" -> {
                            Image(
                                painter = painterResource(id = R.drawable.img_popslide_app),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 32.dp)
                                    .padding(bottom = 96.dp),
                                alignment = Alignment.BottomCenter,
                                contentScale = ContentScale.FillWidth
                            )
                        }

                        else -> ImageWithDeviceFrame(imageSize, projects[page].image)
                    }
                }
            }
        }
    }


    LaunchedEffect(showSheet) {
        if (showSheet.value) {
            bottomSheetState.expand()
        } else {
            bottomSheetState.hide()
        }
    }
}

@Composable
private fun ImageWithDeviceFrame(imageSize: MutableState<IntSize>, image: Int) {
    if (imageSize.value != IntSize.Zero) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopStart,
            modifier = Modifier
                .size(
                    width = imageSize.value.width.dp,
                    height = imageSize.value.height.dp
                )
                .padding(vertical = 40.dp, horizontal = 80.dp)
                .clip(RoundedCornerShape(percent = 10))
                .clipToBounds()
        )
    }

    Image(
        painter = painterResource(id = R.drawable.img_galaxy),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopStart,
        modifier = Modifier
            .padding(vertical = 32.dp)
            .padding(horizontal = 72.dp)
            .onGloballyPositioned { layoutCoordinates ->
                imageSize.value = layoutCoordinates.size
            }
            .then(
                if (imageSize.value != IntSize.Zero) Modifier.size(
                    width = imageSize.value.width.dp,
                    height = imageSize.value.height.dp
                ) else Modifier
            )
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProjectIconAndDescription(
    currentProject: Project,
    scrollState: ScrollState,
    pagerState: PagerState,
    selectedProject: MutableState<Project?>,
    showSheet: MutableState<Boolean>,
    textColor: Color = Color.Black
) {
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            projects.forEachIndexed { index, project ->
                Card(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    shape = RoundedCornerShape(percent = 10),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (index == pagerState.currentPage) 4.dp else 0.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .border(
                            BorderStroke(
                                2.dp,
                                if (index == pagerState.currentPage) MidnightBlue else Color.Transparent
                            ), RoundedCornerShape(percent = 10)
                        )
                        .grayScale(if (index == pagerState.currentPage) 1f else 0f)
                ) {
                    Image(
                        painter = painterResource(id = project.icon),
                        "",
                        modifier = Modifier
                            .size(72.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(percent = 10))
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        Row {
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = currentProject.name,
                style = MaterialTheme.typography.titleLarge,
                color = textColor
            )
            if (!showSheet.value) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "more info",
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MidnightBlue,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(bottom = 4.dp)
                        .clickable {
                            selectedProject.value = currentProject
                            showSheet.value = true
                        }
                )

                Icon(
                    Icons.Default.KeyboardArrowRight,
                    null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp),
                    tint = MidnightBlue
                )
            }

        }

        Text(
            text = currentProject.desc,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 32.dp),
            color = textColor,
            maxLines = 3,
            minLines = 3
        )
    }

}

fun Modifier.grayScale(saturation: Float): Modifier {
    val saturationMatrix = ColorMatrix().apply { setToSaturation(saturation) }
    val saturationFilter = ColorFilter.colorMatrix(saturationMatrix)
    val paint = Paint().apply { colorFilter = saturationFilter }

    return drawWithCache {
        val canvasBounds = Rect(Offset.Zero, size)
        onDrawWithContent {
            drawIntoCanvas {
                it.saveLayer(canvasBounds, paint)
                drawContent()
                it.restore()
            }
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun HomePreview() {
    PortfolioTheme {
        Projects()
    }
}