package com.dsoles.mobile.getyourcats.modules.favorite.network

import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.utils.RequestState
import javax.inject.Inject

class FavoriteRespositoryImp @Inject constructor(private val favoriteDao: MyFavoriteDao) :
    FavoriteRepository {
    override suspend fun getAllFavoritesIds(): RequestState<List<String>> {
        return try {
            RequestState.Success(favoriteDao.getAllFavoritesIds())
        } catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }
    }

    override suspend fun getAllFavorites(): RequestState<List<BreedEntry>> {
        return try {
            RequestState.Success(favoriteDao.getAllFavorites())
        } catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }
    }

    override suspend fun addFavorite(
        breedEntry: BreedEntry
    ): RequestState<Boolean> {
        return try {
            favoriteDao.insertFavorite(
                breedEntry
            )
            RequestState.Success(true)
        } catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }

    }

    override suspend fun removeFavorite(id: String): RequestState<Boolean> {
        return try {
            favoriteDao.deleteFavorite(
                BreedEntry(
                    id,
                    name = "",
                    breedImageUrl = "",
                    origin = "",
                    temperament = "",
                    description = ""
                )
            )
            RequestState.Success(true)
        } catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }
    }

}

interface FavoriteRepository {

    suspend fun getAllFavoritesIds(): RequestState<List<String>>
    suspend fun getAllFavorites(): RequestState<List<BreedEntry>>

    suspend fun addFavorite(
       breedEntry: BreedEntry
    ): RequestState<Boolean>

    suspend fun removeFavorite(id: String): RequestState<Boolean>
}