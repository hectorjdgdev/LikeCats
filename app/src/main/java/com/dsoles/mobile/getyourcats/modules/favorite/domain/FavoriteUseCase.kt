package com.dsoles.mobile.getyourcats.modules.favorite.domain


import com.dsoles.mobile.getyourcats.common.data.FavoriteEntity
import com.dsoles.mobile.getyourcats.modules.favorite.network.FavoriteRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend fun getAllFavoritesIds(): List<String> {
        return favoriteRepository.getAllFavoritesIds()
    }

    suspend fun getAllFavorites(): List<FavoriteEntity> {
        return favoriteRepository.getAllFavorites()
    }

    suspend fun addFavorite(
        id: String, name: String, breedImageUrl: String,
        origin: String,
        temperament: String,
        description: String
    ) {
        return favoriteRepository.addFavorite(
            id,
            name,
            breedImageUrl,
            origin,
            temperament,
            description
        )
    }

    suspend fun removeFavorite(id: String) {
        return favoriteRepository.removeFavorite(id)
    }
}