package com.dsoles.mobile.getyourcats.modules.favorite.network

import com.dsoles.mobile.getyourcats.common.data.FavoriteEntity
import javax.inject.Inject

class FavoriteRespositoryImp @Inject constructor(private val favoriteDao: MyFavoriteDao) :
    FavoriteRepository {
    override suspend fun getAllFavoritesIds(): List<String> {
        return favoriteDao.getAllFavoritesIds()
    }

    override suspend fun getAllFavorites(): List<FavoriteEntity> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun addFavorite(
        id: String,
        name: String,
        breedImageUrl: String,
        origin: String,
        temperament: String,
        description: String
    ) {
        favoriteDao.insertFavorite(FavoriteEntity(id, name, breedImageUrl,origin, temperament, description))
    }

    override suspend fun removeFavorite(id: String) {
        favoriteDao.deleteFavorite(
            FavoriteEntity(
                id,
                name = "",
                breedImageUrl = "",
                origin= "",
                temperament= "",
                description= ""
            )
        )
    }

}

interface FavoriteRepository {

    suspend fun getAllFavoritesIds(): List<String>
    suspend fun getAllFavorites(): List<FavoriteEntity>

    suspend fun addFavorite(
        id: String,
        name: String,
        breedImageUrl: String,
        origin: String,
        temperament: String,
        description: String
    )

    suspend fun removeFavorite(id: String)
}