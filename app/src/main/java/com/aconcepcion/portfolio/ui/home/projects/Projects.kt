package com.aconcepcion.portfolio.ui.home.projects

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.aconcepcion.portfolio.R
import com.aconcepcion.portfolio.ui.theme.MidnightBlue
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Projects() {
    Surface(color = Color.Transparent) {
        Column {
            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = { Projects.values().size })
            val scrollState = rememberScrollState()
            val scrollDistancePx = with(LocalDensity.current) { 88.dp.roundToPx() }

            LaunchedEffect(key1 = pagerState.currentPage) {
                coroutineScope.launch {
                    scrollState.animateScrollTo(pagerState.currentPage * scrollDistancePx)
                }
            }

            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {


                Projects.entries.toTypedArray().forEachIndexed { index, project ->
                    Card(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        shape = RoundedCornerShape(percent = 10),
                        elevation = CardDefaults.cardElevation(defaultElevation = if (index == pagerState.currentPage) 4.dp else 0.dp ),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .border(BorderStroke(2.dp, if (index == pagerState.currentPage) MidnightBlue else Color.Transparent), RoundedCornerShape(percent = 10))
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

            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
                Box {
                    val imageSize = remember { mutableStateOf(IntSize.Zero) }

                    when (page) {
                        1 -> {
                            Image(
                                painter = painterResource(id = R.drawable.img_solo_app),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(vertical = 32.dp)
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        3 -> {
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
                        else -> ImageWithDeviceFrame(imageSize, Projects.entries[page].image)
                    }
                }
            }
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
                .padding(vertical = 40.dp, horizontal = 40.dp)
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
            .padding(32.dp)
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

enum class Projects(val desc: String, val icon: Int, val image: Int) {
    OCBC("OCBC", R.drawable.ic_ocbc_logo, R.drawable.img_ocbc_app),
    SOLO("Solo", R.drawable.ic_solo_logo, R.drawable.img_solo_app),
    OWTO("OWTO", R.drawable.ic_owto_logo, R.drawable.img_owto_app),
    POPSLIDE("Popslide", R.drawable.ic_popslide_logo, R.drawable.img_popslide_app),
    WEBSAFETY("WebSafety", R.drawable.ic_websafety_logo, R.drawable.img_websafety_app),
    PROJECTBASS("ProjectBASS", R.drawable.ic_projectbass_logo, R.drawable.img_projectbass_app),
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