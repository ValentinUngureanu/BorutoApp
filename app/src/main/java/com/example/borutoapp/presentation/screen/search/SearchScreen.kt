package com.example.borutoapp.presentation.screen.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.borutoapp.presentation.common.ListContent


@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = { searchViewModel.updateSearchQuery(query = it) },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },


    ) {innerPadding->

            ListContent(heroes = heroes, navController = navController, modifier = Modifier.padding(innerPadding))
    }


}