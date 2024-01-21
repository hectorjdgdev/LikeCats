package com.dsoles.mobile.getyourcats.di

import com.dsoles.mobile.getyourcats.modules.home.domain.BreedUseCase
import com.dsoles.mobile.getyourcats.modules.home.network.BreedRepository
import com.dsoles.mobile.getyourcats.modules.home.network.BreedRespositoryImp
import com.dsoles.mobile.getyourcats.modules.home.network.BreedService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {
    @Provides
    fun provideBreedRepository(breedService: BreedService): BreedRepository {
        return BreedRespositoryImp(breedService)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object HomeDomainModule {
    @Singleton
    @Provides
    fun provideBreedUseCase(breedRepository: BreedRepository): BreedUseCase {
        return BreedUseCase(breedRepository)
    }

    @Provides
    @Singleton
    fun provideBreedService(retrofit: Retrofit): BreedService {
        return retrofit.create(BreedService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeInterfacesModule {
    @Binds
    abstract fun bindBreedRespositoryI(breedRespositoryImpl: BreedRespositoryImp): BreedRepository
}

