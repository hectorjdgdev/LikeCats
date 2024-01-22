package com.dsoles.mobile.getyourcats.modules.favorite.network

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsoles.mobile.getyourcats.common.data.BreedEntry

@Dao
interface MyFavoriteDao {
    @Query("SELECT * FROM BreedEntry")
    suspend fun getAllFavorites(): List<BreedEntry>

    @Query("SELECT * FROM BreedEntry WHERE id = :id")
    suspend fun getFavoriteById(id: String): BreedEntry

    @Query("SELECT id FROM BreedEntry")
    suspend fun getAllFavoritesIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: BreedEntry)

    @Delete
    suspend fun deleteFavorite(favorite: BreedEntry)

    // Add other queries here
}