package com.dsoles.mobile.getyourcats.modules.home.network

import android.util.Log
import com.dsoles.mobile.getyourcats.modules.home.data.Breed

import javax.inject.Inject

class BreedRespositoryImp @Inject constructor(private val breedService: BreedService) :
    BreedRepository {
    override suspend fun getBreeds(page: Int): List<Breed> {
        return try {
            breedService.getBreeds(page)
        } catch (e: Exception) {
            Log.e("getBreeds", e.message.toString())
            emptyList()
        }
    }

    override suspend fun searchBreeds(search: String): List<Breed> {
        return try {
            breedService.searchBreed(search)
        } catch (e: Exception) {
            Log.e("searchBreeds", e.message.toString())
            emptyList()
        }
    }
}

interface BreedRepository {
    suspend fun searchBreeds(search: String): List<Breed>
    suspend fun getBreeds(page: Int): List<Breed>
}