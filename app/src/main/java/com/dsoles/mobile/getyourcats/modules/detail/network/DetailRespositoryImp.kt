package com.dsoles.mobile.getyourcats.modules.detail.network

import com.dsoles.mobile.getyourcats.common.data.FavoriteEntity
import com.dsoles.mobile.getyourcats.modules.favorite.network.MyFavoriteDao
import javax.inject.Inject

class DetailRespositoryImp @Inject constructor(private val favoriteDao: MyFavoriteDao) :
    DetailRepository {
    override suspend fun getFavorite(id:String): FavoriteEntity {
        return favoriteDao.getFavoriteById(id)
    }


}

interface DetailRepository {
    suspend fun getFavorite(id: String): FavoriteEntity
}