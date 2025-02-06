package com.task.bosta.presentation.cities.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.bosta.data.model.CityModel
import com.task.bosta.domain.usecase.GetCitiesUseCase
import com.task.bosta.utils.network.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(private val useCase: GetCitiesUseCase) : ViewModel() {

    private val _state = MutableLiveData<State<List<CityModel>>>()
    val state: LiveData<State<List<CityModel>>> get() = _state

    fun loadCities() {
        viewModelScope.launch {
            useCase().catch {
                _state.postValue(State.Error(message = it.message))
            }.collect {
                _state.postValue(it)
            }
        }
    }
}