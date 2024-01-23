package com.dsoles.mobile.getyourcats.modules.detail.domain

import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.detail.network.DetailRepository
import com.dsoles.mobile.getyourcats.utils.RequestState
import javax.inject.Inject

class DetailDomainUseCase @Inject constructor(private val detailRepository: DetailRepository) {
    suspend fun getFavorite(id: String): RequestState<BreedEntry> = detailRepository.getFavorite(id)

}