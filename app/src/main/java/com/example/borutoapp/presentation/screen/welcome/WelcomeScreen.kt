package com.example.borutoapp.presentation.screen.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.OnBoardingPage
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.activeIndicatorColor
import com.example.borutoapp.ui.theme.buttonBackgroundColor
import com.example.borutoapp.ui.theme.inactiveIndicatorColor
import com.example.borutoapp.ui.theme.welcomeScreenBackgroundColor
import com.example.borutoapp.util.Constants.LAST_ON_BOARDING_PAGE

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavHostController) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.welcomeScreenBackgroundColor),
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .weight(10f)
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        DotsIndicator(
            totalDots = pages.size,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        }

    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f)
                .padding(bottom = MEDIUM_PADDING),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image),
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = onBoardingPage.title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = onBoardingPage.description,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )

    }
}

@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Finish")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(totalDots) { index ->
            val color =
                if (index == selectedIndex) MaterialTheme.colorScheme.activeIndicatorColor
                else MaterialTheme.colorScheme.inactiveIndicatorColor

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == selectedIndex) 12.dp else 8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}


