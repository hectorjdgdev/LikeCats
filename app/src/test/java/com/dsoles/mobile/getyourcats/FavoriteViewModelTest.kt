package com.dsoles.mobile.getyourcats

import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.favorite.domain.FavoriteUseCase
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteViewModel
import com.dsoles.mobile.getyourcats.utils.RequestState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FavoriteViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var favoriteUseCase: FavoriteUseCase
    private lateinit var viewModel: FavoriteViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        favoriteUseCase = mock()
        viewModel = FavoriteViewModel(favoriteUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFavorites success updates listOfFavorite`() = runTest {

        val breeds = listOf(BreedEntry("1", "Breed 1"), BreedEntry("2", "Breed 2"))
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(RequestState.Success(breeds))

        viewModel.getFavorites()


        assertEquals(breeds, viewModel.listOfFavorite.value)
        assertEquals("", viewModel.loadErrorState.value)
    }

    @Test
    fun `getFavorites error updates loadErrorState`() = runTest {
        val errorMessage = "Error fetching favorites"
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(RequestState.Error(errorMessage))

        viewModel.getFavorites()


        assertEquals(errorMessage, viewModel.loadErrorState.value)
    }

}
