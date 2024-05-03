package com.aconcepcion.portfolio.ui.home


import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aconcepcion.portfolio.R
import com.aconcepcion.portfolio.ui.Intro
import com.aconcepcion.portfolio.ui.home.aboutme.AboutMe
import com.aconcepcion.portfolio.ui.home.experience.Experience
import com.aconcepcion.portfolio.ui.home.experience.Skills
import com.aconcepcion.portfolio.ui.home.projects.Projects

fun NavGraphBuilder.addHomeGraph(
    onNavigate: (String, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.INTRO.route) { from ->
        Intro(onPortfolioClicked = { onNavigate(HomeSections.SKILLS.route, from) })
    }
    composable(
        HomeSections.SKILLS.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(500)
            )
        }
    ) { from ->
        Skills()
    }
    composable(
        HomeSections.PROJECTS.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(500)
            )
        }
    ) { from ->
        Projects()
    }
    composable(
        HomeSections.EXPERIENCE.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(500)
            )
        }
        ) { from ->
        Experience()
    }
    composable(HomeSections.PROFILE.route) {
        AboutMe()
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    INTRO(R.string.home_intro, Icons.Outlined.Home, "intro"),
    SKILLS(R.string.home_skills, Icons.Outlined.ShoppingCart, "home/skills"),
    PROJECTS(R.string.home_projects, Icons.Outlined.ShoppingCart, "home/projects"),
    EXPERIENCE(R.string.home_experience, Icons.Outlined.Search, "home/experience"),
    PROFILE(R.string.home_profile, Icons.Outlined.AccountCircle, "home/profile")
}