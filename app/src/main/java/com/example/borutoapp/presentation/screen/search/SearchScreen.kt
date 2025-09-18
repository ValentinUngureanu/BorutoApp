package com.example.borutoapp.presentation.screen.search

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable


@Composable
fun SearchScreen(){
    Scaffold(
        topBar={
        SearchTopBar(
            text = "",
            onTextChange = {},
            onSearchClicked = {},
            onCloseClicked = {}
        )
        }
    ){}
}