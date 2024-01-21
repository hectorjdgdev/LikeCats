package com.dsoles.mobile.getyourcats.common.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsoles.mobile.getyourcats.common.data.FavoriteEntity
import com.dsoles.mobile.getyourcats.modules.favorite.network.MyFavoriteDao


@Database(entities = [FavoriteEntity::class], version = 1)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun myDao(): MyFavoriteDao

}