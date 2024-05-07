package com.aconcepcion.portfolio

import android.os.Bundle
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.aconcepcion.portfolio.ui.home.HomeSections
import com.aconcepcion.portfolio.ui.home.addHomeGraph
import com.aconcepcion.portfolio.ui.navigation.MainDestinations
import com.aconcepcion.portfolio.ui.navigation.PortfolioNavController
import com.aconcepcion.portfolio.ui.navigation.rememberPortfolioNavController
import com.aconcepcion.portfolio.ui.theme.MidnightBlue
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme
import com.aconcepcion.portfolio.ui.theme.Typography
import com.razaghimahdi.library.CardDrawer
import com.razaghimahdi.library.core.CardDrawerState
import com.razaghimahdi.library.core.CardDrawerValue
import com.razaghimahdi.library.core.rememberCardDrawerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioApp(
    portfolioNavController: PortfolioNavController = rememberPortfolioNavController(),
    drawerState: CardDrawerState = rememberCardDrawerState(initialValue = CardDrawerValue.Closed),
    shouldShowBottomNav: MutableState<Boolean> = remember { mutableStateOf(false) },
    destinationState: MutableState<String> = remember { mutableStateOf("intro") }
) {
    portfolioNavController.navController.addOnDestinationChangedListener { controller, destination, arguments ->
        shouldShowBottomNav.value = destination.route != "intro"
        destinationState.value = destination.route.orEmpty()
    }

    PortfolioTheme {
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.merge(
                TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.asap)
                    )
                )
            )
        ) {
            CardDrawer(
                modifier = Modifier,
                gesturesEnabled = true,
                drawerBackgroundColor = MaterialTheme.colorScheme.surface,
                drawerContentColor = contentColorFor(Color.Blue),
                contentCornerSize = 0.dp,
                contentBackgroundColor = MaterialTheme.colorScheme.surface,
                drawerContent = { DrawerContent(drawerState, portfolioNavController) },
                drawerState = drawerState,
            ) {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        val coroutineScope = rememberCoroutineScope()
                        TopAppBar(
                            title = {},
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                            navigationIcon = {
                                IconButton(
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black),
                                    onClick = {
                                        coroutineScope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                    Icon(
                                        Icons.Rounded.Menu,
                                        contentDescription = "MenuButton"
                                    )
                                }
                            },
                            actions = {
                                if (shouldShowBottomNav.value) {
                                    IconButton(
                                        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black),
                                        onClick = {
                                            coroutineScope.launch {
                                                portfolioNavController.home()
                                            }
                                        }) {
                                        Icon(
                                            Icons.Rounded.Close,
                                            contentDescription = "Close"
                                        )
                                    }
                                }
                            }
                        )
                    },
                ) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            modifier = Modifier
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.White, Color.Cyan),
                                    )
                                )
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = when (destinationState.value) {
                                            HomeSections.SKILLS.route -> listOf(Color(0xFF56CCF2), Color(0xFF00BFFF))
                                            HomeSections.PROJECTS.route -> listOf(Color(0xFF6FCF97), Color(0xFF50C878))
                                            HomeSections.EXPERIENCE.route -> listOf(Color(0xFFC4C4C4), Color(0xFF333333))
                                            HomeSections.PROFILE.route -> listOf(Color(0xFFFAD390), Color(0xFFE55039))
                                            else -> listOf(Color.Gray, Color.Cyan)
                                        } .map { color ->
                                            animateColorAsState(targetValue = color, animationSpec = TweenSpec(durationMillis = 1000),
                                                label = "ColorAnimation"
                                            ).value
                                        },
                                        center = Offset.Zero,
                                        radius = 10f,
                                        tileMode = TileMode.Repeated
                                    ),
                                    alpha = 0.2f
                                )
                                .padding(
                                    start = 0.dp,
                                    top = innerPadding.calculateTopPadding(),
                                    end = 0.dp,
                                    bottom = 0.dp
                                ),
                            navController = portfolioNavController.navController,
                            startDestination = MainDestinations.HOME_ROUTE,
                        ) {
                            portfolioNavGraph(
                                navigateToPortfolio = { route, from ->
                                    portfolioNavController.navigateToBottomBarRoute(route)
                                },
                                onExperienceSelected = { route, from ->

                                },
                                upPress = portfolioNavController::upPress,
                            )
                        }

                        if (shouldShowBottomNav.value) {
                            NavigationBar(
                                tonalElevation = 3.dp,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(horizontal = 16.dp)
                                    .graphicsLayer {
                                        shape = androidx.compose.foundation.shape.RoundedCornerShape(
                                            topStart = 20.dp, topEnd = 20.dp
                                        )
                                        clip = true
                                    }
                            ) {
                                NavigationBarItem(
                                    modifier = Modifier.weight(3f),
                                    icon = { Icon(Icons.Outlined.Build, contentDescription = null) },
                                    label = { Text("Skills", style = Typography.labelSmall, textAlign = TextAlign.Center) },
                                    onClick = {
                                        portfolioNavController.navigateToBottomBarRoute(HomeSections.SKILLS.route)
                                    },
                                    selected = destinationState.value == HomeSections.SKILLS.route
                                )
                                NavigationBarItem(
                                    modifier = Modifier.weight(3f),
                                    icon = { Icon(Icons.Outlined.PlayArrow, contentDescription = null) },
                                    label = { Text("Projects", style = Typography.labelSmall, textAlign = TextAlign.Center) },
                                    onClick = {
                                        portfolioNavController.navigateToBottomBarRoute(HomeSections.PROJECTS.route)
                                    },
                                    selected = destinationState.value == HomeSections.PROJECTS.route
                                )
                                NavigationBarItem(
                                    modifier = Modifier.weight(3f),
                                    icon = { Icon(Icons.Outlined.DateRange, contentDescription = null) },
                                    label = { Text("Experience", style = Typography.labelSmall, textAlign = TextAlign.Center) },
                                    onClick = { portfolioNavController.navigateToBottomBarRoute(HomeSections.EXPERIENCE.route) },
                                    selected = destinationState.value == HomeSections.EXPERIENCE.route
                                )
                                NavigationBarItem(
                                    modifier = Modifier.weight(3f),
                                    icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                                    label = { Text("About Me", style = Typography.labelSmall, textAlign = TextAlign.Center) },
                                    onClick = { portfolioNavController.navigateToBottomBarRoute(HomeSections.PROFILE.route) },
                                    selected = destinationState.value == HomeSections.PROFILE.route
                                )
                            }
                        }

                    }

                }
            }
        }
    }
}

private fun NavGraphBuilder.portfolioNavGraph(
    navigateToPortfolio: (String, NavBackStackEntry) -> Unit,
    onExperienceSelected: (String, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.INTRO.route
    ) {
        addHomeGraph(navigateToPortfolio)
    }

    composable(
        "${MainDestinations.EXPERIENCE_DETAIL_ROUTE}/{${MainDestinations.EXPERIENCE_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.EXPERIENCE_ID_KEY) {
            type = NavType.LongType
        })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val expId = arguments.getLong(MainDestinations.EXPERIENCE_ID_KEY)
    }
}