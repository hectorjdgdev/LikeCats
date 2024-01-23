package com.dsoles.mobile.getyourcats.modules.detail.network

import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.favorite.network.MyFavoriteDao
import com.dsoles.mobile.getyourcats.utils.RequestState
import javax.inject.Inject

class DetailRespositoryImp @Inject constructor(private val favoriteDao: MyFavoriteDao) :
    DetailRepository {
    override suspend fun getFavorite(id:String): RequestState<BreedEntry> {
        return try {
            RequestState.Success(favoriteDao.getFavoriteById(id))
        }catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }
    }


}

interface DetailRepository {
    suspend fun getFavorite(id: String): RequestState<BreedEntry>
}