package com.dsoles.mobile.getyourcats.modules.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.home.domain.BreedUseCase
import com.dsoles.mobile.getyourcats.utils.ConfigApi
import com.dsoles.mobile.getyourcats.utils.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val breedUseCase: BreedUseCase) :
    ViewModel() {
    private val _listBreed = MutableStateFlow<List<BreedEntry>>(
        listOf()
    )
    val listBreed = _listBreed.asStateFlow()

    private var currentPage = mutableStateOf(0)
    private val pageSize = ConfigApi.PAGE_SIZE
    private var isLastPage = mutableStateOf(false)
    private var isLoading = mutableStateOf(false)

    private var searchableText = ""

    var loadErrorState = mutableStateOf("")
    var isLoadingState = mutableStateOf(false)


    init {
        isLoadingState.value = true
    }

    fun activeIsLoadingGeneral() {
        isLoadingState.value = true
        loadErrorState.value = ""
    }

    private fun searchTextUpdate(search: String) {
        if (searchableText != search) {
            searchableText = search
            currentPage.value = 0
            isLastPage.value = false
            _listBreed.value = mutableListOf()
        }
    }

    fun fetchData(search: String = "") {
        searchTextUpdate(search)
        if (isLastPage.value || isLoading.value) return

        viewModelScope.launch {
            try {
                isLoading.value = true
                val breedListResult = breedUseCase.getBreeds(search, currentPage.value)
                when (breedListResult) {
                    is RequestState.Success -> {
                        breedListResult.data?.let {
                            val listBreedEntry = it.map { breed ->
                                BreedEntry(
                                    breed.id,
                                    breed.name,
                                    breed.image?.url ?: "",
                                    breed.origin,
                                    breed.temperament,
                                    breed.description,
                                )
                            }
                            _listBreed.value = _listBreed.value + listBreedEntry
                            currentPage.value++
                            isLastPage.value = it.size < pageSize
                        }
                        loadErrorState.value = ""
                    }

                    is RequestState.Error -> {
                        breedListResult.message?.let {
                            loadErrorState.value = it
                        }
                    }
                }
            } catch (e: Exception) {
                loadErrorState.value = e.message ?: "Generic Error"
            }
            isLoading.value = false
            isLoadingState.value = false

        }
    }

}