package com.dsoles.mobile.getyourcats.modules.home.domain



import com.dsoles.mobile.getyourcats.modules.home.data.Breed
import com.dsoles.mobile.getyourcats.modules.home.network.BreedRepository
import javax.inject.Inject

class BreedUseCase @Inject constructor(private val breedRepository: BreedRepository) {
    suspend fun getBreeds(search: String = "", page: Int = 0): List<Breed> {
        return if (search.isEmpty()) {
            breedRepository.getBreeds(page)
        } else {
            breedRepository.searchBreeds(search)
        }
    }


}