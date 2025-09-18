package com.example.borutoapp.presentation.screen.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery
    private val _searchHeroes=MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchHeroes=_searchHeroes
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch {
            useCases.searchHeroesUseCase(query = query).cachedIn(viewModelScope).collect {
                _searchHeroes.value = it
            }

        }
    }
}