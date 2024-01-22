package com.dsoles.mobile.getyourcats.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoles.mobile.getyourcats.modules.favorite.domain.FavoriteUseCase
import com.dsoles.mobile.getyourcats.modules.home.data.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _listFavorites = MutableStateFlow<Set<String>>(setOf())
    val listFavorites = _listFavorites.asStateFlow()


    init {
        getListFavoritesIds()
    }

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun getListFavoritesIds() {
        viewModelScope.launch {
            _listFavorites.value = favoriteUseCase.getAllFavoritesIds().toSet()
        }

    }
}