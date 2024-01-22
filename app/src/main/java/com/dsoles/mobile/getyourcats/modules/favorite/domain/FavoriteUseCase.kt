package com.dsoles.mobile.getyourcats.modules.favorite.domain


import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.favorite.network.FavoriteRepository
import com.dsoles.mobile.getyourcats.utils.RequestState
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend fun getAllFavoritesIds(): RequestState<List<String>> {
        return favoriteRepository.getAllFavoritesIds()
    }

    suspend fun getAllFavorites(): RequestState<List<BreedEntry>> {
        return favoriteRepository.getAllFavorites()
    }

    suspend fun addFavorite(
        breedEntry: BreedEntry
    ): RequestState<Boolean> {
        return favoriteRepository.addFavorite(
            breedEntry
        )
    }

    suspend fun removeFavorite(id: String): RequestState<Boolean> {
        return favoriteRepository.removeFavorite(id)
    }
}