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
    fun `averageLifeSpanQuery correctly calculates average lifespan`() = runTest {
        // Arrange
        val favorites = listOf(BreedEntry("1", "Breed 1", lifeSpan = "10 - 12"), BreedEntry("2", "Breed 2", lifeSpan = "8 - 10"))
        whenever(viewModel.listOfFavorite.value).thenReturn(favorites)

        viewModel.averageLifeSpanQuery()

        assertEquals(9, viewModel.averageLifeSpan.value)  // Assuming average lifespan calculation logic is correct
    }

    @Test
    fun `getFavorites error updates loadErrorState`() = runTest {
        val errorMessage = "Error fetching favorites"
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(RequestState.Error(errorMessage))

        viewModel.getFavorites()


        assertEquals(errorMessage, viewModel.loadErrorState.value)
    }

    @Test
    fun `averageLifeSpanQuery calculates correct average lifespan`() = runTest {
        val favorites = listOf(
            BreedEntry("1", "Breed 1", lifeSpan = "10-12"),
            BreedEntry("2", "Breed 2", lifeSpan = "8 - 10"),
            BreedEntry("3", "Breed 3", lifeSpan = "12 - 15")
        )
        whenever(favoriteUseCase.getAllFavorites()).thenReturn(RequestState.Success(favorites))

        viewModel.getFavorites()
        viewModel.averageLifeSpanQuery()

        val expectedAverageLifeSpan = (10 + 8 + 12) / 3 // Assuming this is how average is calculated
        assertEquals(expectedAverageLifeSpan, viewModel.averageLifeSpan.value)
    }

}
