package com.aconcepcion.portfolio

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aconcepcion.portfolio.ui.home.HomeSections
import com.aconcepcion.portfolio.ui.navigation.PortfolioNavController
import com.razaghimahdi.library.core.CardDrawerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(drawerState: CardDrawerState, portfolioNavController: PortfolioNavController) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painterResource(id = R.drawable.img_avatar),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(128.dp)
                        .clip(RoundedCornerShape(percent = 10))
                        .border(2.dp, Color.White, RoundedCornerShape(percent = 10))
                )

                Spacer(Modifier.weight(1.0f))

                Card(
                    onClick = { coroutineScope.launch { drawerState.close() } },
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Icon(
                        Icons.Default.Close,
                        null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(4.dp)
                            .size(24.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Spacer(modifier = Modifier.width(8.dp))

                    Column() {
                        Text(
                            text = "Andrew Concepcion",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            text = "Mobile Developer",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Menu",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        drawerState.close()
                        portfolioNavController.home()
                    }
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    Icons.Default.Home,
                    null,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Home", style = MaterialTheme.typography.bodyLarge
                )
            }

            Divider(
                color = DividerDefaults.color,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        drawerState.close()
                        portfolioNavController.navigateToBottomBarRoute(HomeSections.SKILLS.route)
                    }
                },
            ) {
                Icon(
                    Icons.Default.Info,
                    null,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Know more", style = MaterialTheme.typography.bodyLarge
                )
            }

            Divider(
                color = DividerDefaults.color,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )


//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Start
//            ) {
//
//                Icon(
//                    Icons.Default.Search,
//                    null,
//                    modifier = Modifier.padding(8.dp)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = "Ask AI about me", style = MaterialTheme.typography.bodyLarge
//                )
//            }

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                text = "Contact",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:<removed>")
                    }
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    Icons.Default.Call,
                    null,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "tel:<removed>", style = MaterialTheme.typography.bodyLarge,
                )
            }

            Divider(
                color = DividerDefaults.color,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )



            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:<removed>")
                    }
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    Icons.Default.Email,
                    null,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "<removed>", style = MaterialTheme.typography.bodyLarge
                )
            }

            Divider(
                color = DividerDefaults.color,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )


        }

    }

}