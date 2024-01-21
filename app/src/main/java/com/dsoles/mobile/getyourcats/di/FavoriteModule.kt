package com.dsoles.mobile.getyourcats.di

import com.dsoles.mobile.getyourcats.modules.favorite.domain.FavoriteUseCase
import com.dsoles.mobile.getyourcats.modules.favorite.network.FavoriteRepository
import com.dsoles.mobile.getyourcats.modules.favorite.network.FavoriteRespositoryImp
import com.dsoles.mobile.getyourcats.modules.favorite.network.MyFavoriteDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object FavoriteModule {
    @Provides
    fun provideFavoriteRespositoryImp(favoriteDao: MyFavoriteDao): FavoriteRespositoryImp {
        return FavoriteRespositoryImp(favoriteDao)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDomainModule {
    @Singleton
    @Provides
    fun provideFavoriteUseCase(favoriteRepository: FavoriteRepository): FavoriteUseCase {
        return FavoriteUseCase(favoriteRepository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfacesModule {
    @Binds
    abstract fun bindFavoriteRepositoryI(favoriteRepository: FavoriteRespositoryImp): FavoriteRepository
}

