package com.dsoles.mobile.getyourcats

import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.detail.domain.DetailDomainUseCase
import com.dsoles.mobile.getyourcats.modules.detail.viewmodel.DetailViewModel
import com.dsoles.mobile.getyourcats.utils.RequestState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
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
class DetailViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var detailDomainUseCase: DetailDomainUseCase
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        detailDomainUseCase = mock()
        viewModel = DetailViewModel(detailDomainUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFavorite success updates favorite`() = runTest {
        val breedId = "1"
        val breedEntry = BreedEntry(breedId, "Breed 1")
        whenever(detailDomainUseCase.getFavorite(breedId)).thenReturn(
            RequestState.Success(
                breedEntry
            )
        )

        viewModel.getFavorite(breedId, isFavorite = true)

        assertEquals(breedEntry, viewModel.favorite.value)
        assertEquals("", viewModel.loadErrorState.value)
    }

    @Test
    fun `getFavorite error updates loadErrorState`() = runTest {

        val breedId = "1"
        val errorMessage = "Error fetching favorite"
        whenever(detailDomainUseCase.getFavorite(breedId)).thenReturn(
            RequestState.Error(
                errorMessage
            )
        )

        viewModel.getFavorite(breedId, isFavorite = true)


        assertNull(viewModel.favorite.value)
        assertEquals(errorMessage, viewModel.loadErrorState.value)
    }
}
