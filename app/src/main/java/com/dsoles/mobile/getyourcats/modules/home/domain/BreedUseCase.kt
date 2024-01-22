package com.dsoles.mobile.getyourcats.modules.home.domain



import androidx.compose.foundation.pager.PageSize
import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.home.data.Breed
import com.dsoles.mobile.getyourcats.modules.home.network.BreedRepository
import com.dsoles.mobile.getyourcats.utils.ConfigApi
import com.dsoles.mobile.getyourcats.utils.RequestState
import javax.inject.Inject

class BreedUseCase @Inject constructor(private val breedRepository: BreedRepository) {
    suspend fun getBreeds(
        search: String = "",
        page: Int = 0,
        pageSize: Int = ConfigApi.PAGE_SIZE
    ): RequestState<List<BreedEntry>> {
        val result = if (search.isEmpty()) {
            breedRepository.getBreeds(pageSize = pageSize, page = page)

        } else {
            breedRepository.searchBreeds(search)
        }
        val list = result.data!!.map {
            BreedEntry(
                it.id,
                it.name,
                it.image?.url ?: "",
                it.origin,
                it.temperament,
                it.description,
            )
        }
        return RequestState.Success(list)

    }


}