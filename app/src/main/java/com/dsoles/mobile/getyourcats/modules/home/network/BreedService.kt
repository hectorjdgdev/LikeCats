package com.dsoles.mobile.getyourcats.modules.home.network


import com.dsoles.mobile.getyourcats.modules.home.data.Breed
import com.dsoles.mobile.getyourcats.utils.ConfigApi
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BreedService {
    @GET("v1/breeds?limit=10")
    suspend fun getBreeds(
        @Query("limit") limit: Int = ConfigApi.PAGE_SIZE,
        @Query("page") page: Int = 0,
        @Header("x-api-key") apiKey: String = ConfigApi.API_KEY
    ): List<Breed>

    @GET("v1/breeds/search?attach_image=1")
    suspend fun searchBreed(
        @Query("q") search: String,
        @Header("x-api-key") apiKey: String = ConfigApi.API_KEY
    ): List<Breed>

}