package com.dsoles.mobile.getyourcats

import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.modules.home.domain.BreedUseCase
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.HomeViewModel
import com.dsoles.mobile.getyourcats.utils.RequestState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.whenever
import org.mockito.stubbing.OngoingStubbing

class HomeViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var breedUseCase: BreedUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        breedUseCase = mock()
        viewModel = HomeViewModel(breedUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchData success updates listBreed`() = runTest {
        val breeds =
            listOf(BreedEntry("1", "Breed 1", null, "Origin 1", "Temperament 1", "Description 1"))
        whenever(breedUseCase.getBreeds()).thenReturn(RequestState.Success(breeds))

        viewModel.fetchData()

        assertEquals(1, viewModel.listBreed.value.size)
        assertEquals("Breed 1", viewModel.listBreed.value[0].name)
    }

    @Test
    fun `fetchData error updates loadErrorState`() = runTest {
        val errorMessage = "Error fetching breeds"
        whenever(breedUseCase.getBreeds()).thenReturn(RequestState.Error(errorMessage))

        viewModel.fetchData()

        assertEquals(errorMessage, viewModel.loadErrorState.value)
        assertTrue(viewModel.listBreed.value.isEmpty())
    }
}
