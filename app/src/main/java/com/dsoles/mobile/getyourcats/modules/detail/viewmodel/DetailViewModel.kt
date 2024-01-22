package com.dsoles.mobile.getyourcats.modules.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoles.mobile.getyourcats.common.data.FavoriteEntity
import com.dsoles.mobile.getyourcats.common.repository.SharedDataRepository
import com.dsoles.mobile.getyourcats.modules.detail.domain.DetailDomainUseCase
import com.dsoles.mobile.getyourcats.modules.favorite.domain.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailDomainUseCase: DetailDomainUseCase) :
    ViewModel() {

    private val _favorite = MutableStateFlow<FavoriteEntity?>(null)
    val favorite = _favorite.asStateFlow()


    fun getFavorite(id: String,isFavorite: Boolean = false) {
        viewModelScope.launch {
            try {
                _favorite.value = if(isFavorite){
                    detailDomainUseCase.getFavorite(id)
                }else{
                    SharedDataRepository.breedSelected
                }
            } catch (e: Exception) {

            }
        }
    }
}