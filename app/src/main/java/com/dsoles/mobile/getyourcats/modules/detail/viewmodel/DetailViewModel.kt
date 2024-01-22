package com.dsoles.mobile.getyourcats.modules.detail.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.common.repository.SharedDataRepository
import com.dsoles.mobile.getyourcats.modules.detail.domain.DetailDomainUseCase
import com.dsoles.mobile.getyourcats.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailDomainUseCase: DetailDomainUseCase) :
    ViewModel() {

    private val _favorite = MutableStateFlow<BreedEntry?>(null)
    val favorite = _favorite.asStateFlow()

    var loadErrorState = mutableStateOf("")
    var isLoadingState = mutableStateOf(false)


    fun getFavorite(id: String, isFavorite: Boolean = false) {
        viewModelScope.launch {
            isLoadingState.value = true
            try {
                if (isFavorite) {
                    val favorite = detailDomainUseCase.getFavorite(id)
                    when (favorite) {
                        is RequestState.Success -> {
                            _favorite.value = favorite.data
                            loadErrorState.value = ""
                        }

                        is RequestState.Error -> {
                            loadErrorState.value = favorite.message ?: "Generic Error"
                        }
                    }
                } else {
                    _favorite.value = SharedDataRepository.breedSelected
                    loadErrorState.value = ""
                }
            } catch (e: Exception) {
                loadErrorState.value = e.message.toString()
            }
        }
        isLoadingState.value = false
    }
}