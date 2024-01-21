package com.dsoles.mobile.getyourcats.modules.favorite.network

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsoles.mobile.getyourcats.common.data.FavoriteEntity

@Dao
interface MyFavoriteDao {
    @Query("SELECT * FROM FavoriteEntity")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Query("SELECT id FROM FavoriteEntity")
    suspend fun getAllFavoritesIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    // Add other queries here
}