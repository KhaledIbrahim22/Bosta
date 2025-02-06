package com.task.bosta.presentation.cities.viewModel

import androidx.lifecycle.Observer
import com.task.bosta.data.model.BaseResponse
import com.task.bosta.data.model.CityModel
import com.task.bosta.data.model.DistrictModel
import com.task.bosta.domain.usecase.GetCitiesUseCase
import com.task.bosta.utils.network.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class CitiesViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var useCase: GetCitiesUseCase

    @Mock
    private lateinit var observer: Observer<State<List<CityModel>>>

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun loadCitiesSuccessState() = runTest {
        val cities = listOf(
            CityModel(
                cityName = "Alexandria",
                districts = listOf(
                    DistrictModel(
                        districtName = "Abu Yousef", true
                    )
                ),
            ), CityModel(
                cityName = "Giza",
                districts = listOf(
                    DistrictModel(
                        districtName = "6th of October", false
                    )
                ),
            )
        )
        val successState = State.Success(BaseResponse(success = true, message = null, data = cities))

        `when`(useCase()).thenReturn(flow { emit(successState) })

        val viewModel = CitiesViewModel(useCase)
        viewModel.state.observeForever(observer)
        // Act
        viewModel.loadCities()

        verify(observer).onChanged(successState)
        assertEquals(successState, viewModel.state.value)
    }

    @Test
    fun loadCitiesErrorState() = runTest {
        val errorMessage = "Network error"

        `when`(useCase()).thenReturn(flow { emit(State.Error(errorMessage)) })


        val viewModel = CitiesViewModel(useCase)
        viewModel.state.observeForever(observer)

        viewModel.loadCities()

        verify(observer).onChanged(State.Error(errorMessage))
        assertEquals(State.Error(errorMessage), viewModel.state.value)
    }

    @After
    fun cleanupDispatcher() {
        Dispatchers.resetMain()
    }
}