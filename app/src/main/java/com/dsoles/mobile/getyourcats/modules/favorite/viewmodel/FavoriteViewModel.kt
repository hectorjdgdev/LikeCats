package com.dsoles.mobile.getyourcats.modules.favorite.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.favorite.domain.FavoriteUseCase
import com.dsoles.mobile.getyourcats.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    ViewModel(), FavoriteEventHandler {
    private val _listOfFavorite = MutableStateFlow<List<BreedEntry>>(listOf())
    val listOfFavorite = _listOfFavorite.asStateFlow()

    private val _averageLifeSpan = MutableStateFlow(0)
    val averageLifeSpan = _averageLifeSpan.asStateFlow()

    private val _listFavDB = MutableStateFlow<MutableSet<String>>(
        mutableSetOf()
    )
    val listFavDB = _listFavDB.asStateFlow()

    var loadErrorState = mutableStateOf("")
    var isLoadingState = mutableStateOf(false)

    init {
        getFavorites()
    }


    override fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.FavoriteAddClicked -> addFavorite(
                event.breed
            )

            is FavoriteEvent.FavoriteRemoveClicked -> deleteFavorite(event.breed.id)
        }
    }


    private fun updateFavList(favorites: List<BreedEntry>) {
        val listFav = mutableSetOf<String>()
        favorites.map {
            listFav.add(it.id)
        }
        _listFavDB.value = listFav
    }


    fun getFavorites() {
        viewModelScope.launch {
            try {
                isLoadingState.value = true
                val favorites = favoriteUseCase.getAllFavorites()
                when (favorites) {
                    is RequestState.Success -> {
                        favorites.data?.let {
                            _listOfFavorite.value = it
                            updateFavList(it)
                        }
                    }

                    is RequestState.Error -> {
                        favorites.message?.let {
                            loadErrorState.value = it
                        }
                    }
                }
            } catch (e: Exception) {
                loadErrorState.value = e.message ?: "Generic Error"
            }
            isLoadingState.value = false
        }
    }

    fun averageLifeSpanQuery(){
        val listOfFavoriteSpan = listOfFavorite.value.map {
            it.lifeSpan ?: ""
        }
        _averageLifeSpan.value =
            averageLifespanLowerValue(listOfFavoriteSpan)
    }
   private fun averageLifespanLowerValue(lifespanList: List<String>): Int {
        var sum = 0
        var count = 0

        lifespanList.forEach { lifespan ->
            val numbers = try {
                lifespan.split("-")[0].replace(" ", "").toInt()
            } catch (e: Exception) {
                0
            }
            sum += numbers
            count++
        }
        return if (count > 0) (sum / count) else 0
    }

    private fun addFavorite(
        breedEntry: BreedEntry
    ) {
        viewModelScope.launch {
            favoriteUseCase.addFavorite(breedEntry)
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