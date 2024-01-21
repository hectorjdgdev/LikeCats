package com.dsoles.mobile.getyourcats.di

import android.content.Context
import androidx.room.Room
import com.dsoles.mobile.getyourcats.common.dao.MyAppDatabase
import com.dsoles.mobile.getyourcats.modules.favorite.network.MyFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MyAppDatabase {
        return Room.databaseBuilder(
            appContext,
            MyAppDatabase::class.java,
            "my_database"
        ).build()
    }

    @Provides
    fun provideMyDao(database: MyAppDatabase): MyFavoriteDao {
        return database.myDao()
    }
}