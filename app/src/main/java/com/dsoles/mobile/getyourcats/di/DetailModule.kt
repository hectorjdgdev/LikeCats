package com.dsoles.mobile.getyourcats.di

import com.dsoles.mobile.getyourcats.modules.detail.domain.DetailDomainUseCase
import com.dsoles.mobile.getyourcats.modules.detail.network.DetailRepository
import com.dsoles.mobile.getyourcats.modules.detail.network.DetailRespositoryImp
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
object DetailModule {
    @Provides
    fun provideDetailRespositoryImp(favoriteDao: MyFavoriteDao): DetailRespositoryImp {
        return DetailRespositoryImp(favoriteDao)
    }

}


@Module
@InstallIn(SingletonComponent::class)
object DetailDomainModule {
    @Singleton
    @Provides
    fun provideDetailUseCase(detailRespository: DetailRepository): DetailDomainUseCase {
        return DetailDomainUseCase(detailRespository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailInterfacesModule {
    @Binds
    abstract fun bindDetailRepositoryI(detailRespository: DetailRespositoryImp): DetailRepository
}

