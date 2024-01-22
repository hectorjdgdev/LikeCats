package com.dsoles.mobile.getyourcats.modules.home.network

import com.dsoles.mobile.getyourcats.modules.home.data.Breed
import com.dsoles.mobile.getyourcats.utils.ConfigApi
import com.dsoles.mobile.getyourcats.utils.RequestState
import javax.inject.Inject

class BreedRespositoryImp @Inject constructor(private val breedService: BreedService) :
    BreedRepository {
    override suspend fun getBreeds(page: Int,pageSize: Int): RequestState<List<Breed>> {
        return try {
            RequestState.Success(breedService.getBreeds(page))
        } catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }
    }

    override suspend fun searchBreeds(search: String): RequestState<List<Breed>> {
        return try {
            RequestState.Success(breedService.searchBreed(search))
        } catch (e: Exception) {
            RequestState.Error(e.message.toString())
        }
    }
}

interface BreedRepository {

    suspend fun getBreeds(page: Int, pageSize: Int): RequestState<List<Breed>>

    suspend fun searchBreeds(search: String): RequestState<List<Breed>>
}