package com.dsoles.mobile.getyourcats.modules.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoles.mobile.getyourcats.modules.home.data.Breed
import com.dsoles.mobile.getyourcats.modules.home.domain.BreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val breedUseCase: BreedUseCase) :
    ViewModel() {
    private val _listBreed = MutableStateFlow<List<Breed>>(
        listOf()
    )
    val listBreed = _listBreed.asStateFlow()

    private var currentPage = 0
    private val pageSize = 10
    private var isLastPage = false
    private var isLoading = false

    private var searchableText = ""

    private fun searchTextUpdate(search: String) {
        if (searchableText != search) {
            searchableText = search
            currentPage = 0
            isLastPage = false
            _listBreed.value = mutableListOf()
        }
    }

    init {
//        fetchData()
    }

    fun fetchData(search: String = "") {
        searchTextUpdate(search)
        if (isLastPage || isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                val breedListResult = breedUseCase.getBreeds(search, currentPage)
                _listBreed.value = _listBreed.value + breedListResult
                currentPage++
                isLastPage = breedListResult.size < pageSize
            } catch (e: Exception) {

            }
            isLoading = false
        }
    }

}