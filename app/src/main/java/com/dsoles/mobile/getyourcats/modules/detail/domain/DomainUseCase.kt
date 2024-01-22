package com.dsoles.mobile.getyourcats.modules.detail.domain

import com.dsoles.mobile.getyourcats.modules.detail.network.DetailRepository
import javax.inject.Inject

class DetailDomainUseCase @Inject constructor(private val detailRepository: DetailRepository) {
    suspend fun getFavorite(id: String) = detailRepository.getFavorite(id)

}