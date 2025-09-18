package com.example.borutoapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.presentation.components.RatingWidget
import com.example.borutoapp.presentation.components.ShimmerEffect
import com.example.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.topAppBarContentColor
import com.example.borutoapp.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController,
    modifier: Modifier=Modifier,
) {
    val result = handlePagingResult(heroes = heroes,modifier= modifier)
    if (result) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)

        ) {
            items(
                count = heroes.itemCount,
                key = { index -> heroes[index]?.id ?: index }
            ) { index ->
                val hero = heroes[index]
                if (hero != null) {
                    HeroItem(hero = hero, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>,
    modifier: Modifier
    ): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            error != null -> {
                EmptyScreen(error = error)
                false
            }


            else -> true
        }
    }

}


@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController,
) {

    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}") {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
    Box(

        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(
            shape = RoundedCornerShape(
                size = LARGE_PADDING
            )
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )

        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.45f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.45f),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = MaterialTheme.colorScheme.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

            }
        }


    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Naruto Uzumaki",
            about = "Naruto Uzumaki is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth.",
            rating = 4.5,
            power = 9000,
            month = "October",
            day = "10th",
            family = listOf("Minato Namikaze", "Kushina Uzumaki"),
            abilities = listOf("Rasengan", "Shadow Clone Technique", "Sage Mode"),
            natureTypes = listOf("Wind Release", "Yin Release", "Yang Release"),
            image = "/images/naruto.png"
        ),
        navController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current)
    )
}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HeroItemDarkPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Naruto Uzumaki",
            about = "Naruto Uzumaki is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth.",
            rating = 4.5,
            power = 9000,
            month = "October",
            day = "10th",
            family = listOf("Minato Namikaze", "Kushina Uzumaki"),
            abilities = listOf("Rasengan", "Shadow Clone Technique", "Sage Mode"),
            natureTypes = listOf("Wind Release", "Yin Release", "Yang Release"),
            image = "/images/naruto.png"
        ),
        navController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current)
    )
}