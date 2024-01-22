package com.dsoles.mobile.getyourcats.modules.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoles.mobile.getyourcats.common.data.FavoriteEntity
import com.dsoles.mobile.getyourcats.modules.favorite.domain.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    ViewModel(), FavoriteEventHandler {
    private val _listOfFavorite = MutableStateFlow<List<FavoriteEntity>>(listOf())
    val listOfFavorite = _listOfFavorite.asStateFlow()

    private val _listFavDB = MutableStateFlow<MutableSet<String>>(
        mutableSetOf()
    )
    val listFavDB = _listFavDB.asStateFlow()

    init {
        getFavorites()
    }


    override fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.FavoriteAddClicked -> addFavorite(
                event.id,
                event.name,
                event.breedImageUrl,
                event.origin,
                event.temperament,
                event.description
            )

            is FavoriteEvent.FavoriteRemoveClicked -> deleteFavorite(event.id)
        }
    }


    private fun updateFavList(favorites: List<FavoriteEntity>) {
        val listFav = mutableSetOf<String>()
        favorites.map {
            listFav.add(it.id)
        }
        _listFavDB.value = listFav
    }

    fun getFavorites() {
        viewModelScope.launch {
            try {
                val favorites = favoriteUseCase.getAllFavorites()
                _listOfFavorite.value = favorites
                updateFavList(favorites)
            } catch (e: Exception) {

            }
        }
    }

    private fun addFavorite(
        id: String, name: String, breedImageUrl: String,
        origin: String,
        temperament: String,
        description: String
    ) {
        viewModelScope.launch {
            favoriteUseCase.addFavorite(id, name, breedImageUrl, origin, temperament, description)
            getFavorites()
        }
    }

    private fun deleteFavorite(id: String) {
        viewModelScope.launch {
            favoriteUseCase.removeFavorite(id)
            getFavorites()
        }
    }

}