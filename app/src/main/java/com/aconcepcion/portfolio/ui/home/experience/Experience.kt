package com.aconcepcion.portfolio.ui.home.experience

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aconcepcion.portfolio.ui.theme.PortfolioTheme

@Composable
fun Experience() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .padding(bottom = 80.dp)
    ) {

        Text(
            text = "Experience",
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.displayMedium,
        )

        Experience.entries.forEach { experience ->
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(size = 16.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .animateContentSize()
                ) {
                    val expanded = remember { mutableStateOf(false) }

                    Text(
                        text = experience.position,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Text(
                        text = experience.company,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Text(text = experience.website,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(experience.website)
                            }
                            context.startActivity(intent)
                        })
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = experience.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (expanded.value) {
                        Text(
                            text = experience.summary,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Show Less",
                            modifier = Modifier.clickable { expanded.value = false },
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodySmall,
                            textDecoration = TextDecoration.Underline
                        )
                    } else {
                        Text(
                            text = experience.excerpt + "...",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Read More",
                            modifier = Modifier.clickable { expanded.value = true },
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodySmall,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

            }
        }

        Spacer(Modifier.padding(64.dp))

    }


}

enum class Experience(
    val company: String,
    val website: String,
    val position: String,
    val date: String,
    val excerpt: String,
    val summary: String
) {
    RED_AIRSHIP(
        "RedAirship Pte Ltd.",
        "https://redairship.com",
        "Senior Android Developer",
        "June 2021 – Present",
        """I have been integral from the project's inception, facilitating seamless coordination between our client's in-house team and our internal teams for iOS and Android.""",
        """
            I have been integral from the project's inception, facilitating seamless coordination between our client's in-house team and our internal teams for iOS and Android. This role requires adept navigation through architecture decisions, ensuring alignment across platforms and preemptively addressing potential cross-platform issues. My contributions span critical features such as Onboarding, Bank Accounts List, Account Dashboard with Graphs, Funds Transfer, and Account Opening—with specialized processes for Singapore and Hong Kong. My involvement underscores not only technical expertise but also strategic thinking in multi-platform development environments.
            
            Additionally, we partnered with a third party, commissioned by our client, to develop an SDK integral to our project. My role expanded to include deep technical liaison work—debugging and integrating the third-party SDK to ensure seamless operation within our application. This complex collaboration highlighted my technical acumen, strategic approach to problem-solving, and my capacity to bridge gaps between diverse development teams, underlining the value I bring to multi-faceted development environments.
        """.trimIndent()
    ),
    SOLO(
        "Solo",
        "https://www.getsolo.io",
        "Lead Android Developer",
        "May 2020 – June 2021",
        """Led the Android development team and occasionally provided insights into iOS project components. My cross-functional role underlined the importance of versatility in modern app development environments, including adapting quickly to different technologies and platforms.""",
        """
            Led the Android development team and occasionally provided insights into iOS project components. My cross-functional role underlined the importance of versatility in modern app development environments, including adapting quickly to different technologies and platforms.
            
            As a Lead for the offshore development team of the MySTC app, I oversaw the creation of  new features and maintenance of existing ones for the app, which provides a seamless and  efficient way for users to manage their mobile and landline numbers with STC. The app  offers various services and features, such as viewing and paying bills, recharging prepaid numbers, subscribing to packages and services, monitoring data usage, redeeming loyalty points, and locating branches and Wi-Fi spots.
              
            The app was developed using Clean Architecture and MVVM design pattern, and  implements popular libraries such as RxJava/RxKotlin, Gson, and Retrofit to ensure stability  and ease of use for millions of users in Saudi Arabia and other Gulf countries. In my role as  Lead, I guided the team in following processes, provided mentorship, and ensured that the team met its objectives effectively. 
        """.trimIndent()
    ),
    IPARA(
        "IPara Technologies and Solutions",
        "https://owtoph.com",
        "Development Team Lead",
        "June 2018 – May 2020",
        """Spearheaded the development of OWTO, a tailored transportation solution designed specifically for the Philippine market, enhancing the commute experience through technology-driven services.""",
        """
            Spearheaded the development of OWTO, a tailored transportation solution designed specifically for the Philippine market, enhancing the commute experience through technology-driven services.
            
            Led a cross-functional team in the utilization of Uber's RIB architecture to develop robust Android and iOS applications, demonstrating my expertise in managing multi-platform projects under rigorous timelines.
            
            Orchestrated the integration of a real-time security system within the app, enabling drivers to livestream the vehicle's cabin during emergencies, thereby boosting user safety and trust.
            
            Developed and implemented a patent-pending dispute resolution system, allowing for the effective handling of incidents between drivers and riders by accessing trip details and cabin recordings, highlighting my ability to innovate and implement complex system features. 
        """.trimIndent()
    ),
    YOYO(
        "YOYO Holdings Pte. Ltd.",
        "https://yoyo-holdings.com",
        "Senior Android Developer",
        "June 2016 - June 2018",
        "Led the technical team to revamp the app's architecture, enhancing stability and user experience.",
        """
            Led the technical team to revamp the app's architecture, enhancing stability and user experience. 
            
            My work here, although focused on Android, was part of a broader context that required understanding and integrating with other platform technologies, which complements my adaptability and expertise in a cross-platform development setting.
        """.trimIndent(),
    ),
    ISBX(
        "Internet Strategy Branding and Execution",
        "https://www.isbx.com",
        "Full Stack Web and Mobile Developer",
        "May 2014 - June 2016",
        "Contributed to multiple facets of the WebSafety platform, including Android and iOS development.",
        """ 
            Contributed to multiple facets of the WebSafety platform, including Android and iOS development. This role fortified my ability to navigate and contribute effectively across various platforms, highlighting my utility in roles requiring comprehensive mobile development skills.
        """.trimIndent(),
    )
}


@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun ExperiencePreview() {
    PortfolioTheme {
        Experience()
    }
}
