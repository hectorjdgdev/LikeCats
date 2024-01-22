package com.dsoles.mobile.getyourcats.modules.home.domain



import androidx.compose.foundation.pager.PageSize
import com.dsoles.mobile.getyourcats.modules.home.data.Breed
import com.dsoles.mobile.getyourcats.modules.home.network.BreedRepository
import com.dsoles.mobile.getyourcats.utils.ConfigApi
import com.dsoles.mobile.getyourcats.utils.RequestState
import javax.inject.Inject

class BreedUseCase @Inject constructor(private val breedRepository: BreedRepository) {
    suspend fun getBreeds(search: String = "", page: Int = 0, pageSize: Int = ConfigApi.PAGE_SIZE): RequestState<List<Breed>> {
        return if (search.isEmpty()) {
            breedRepository.getBreeds(page,pageSize)
        } else {
            breedRepository.searchBreeds(search)
        }
    }


}