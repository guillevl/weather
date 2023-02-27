package com.example.weather.climas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.models.Tiempo5DiasResponse
import com.example.weather.data.models.TiempoActualResponse
import com.example.weather.data.remote.ApiRest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    val tiempoActual = MutableStateFlow(listOf<TiempoActualResponse>())
    val tiempo5Dias= MutableStateFlow(listOf<Tiempo5DiasResponse>())
    val loading = MutableStateFlow(false)

    fun getTiempoActual() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiRest.service.getTiempoActual()
            if (response.isSuccessful && response.body() != null) {
                var listatiempo = listOf<TiempoActualResponse>(response.body()!!)
                tiempoActual.value = listatiempo
            } else {
                Log.i("GenresViewModel", "getGenres: ${response.errorBody()?.string()}")
            }

            loading.value = false

        }

    }
    fun getTiempo5Dias() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiRest.service.getTiempo5Dias()
            if (response.isSuccessful && response.body() != null) {
                var listatiempo = listOf<Tiempo5DiasResponse>(response.body()!!)
                tiempo5Dias.value = listatiempo
            } else {
                Log.i("GenresViewModel", "getGenres: ${response.errorBody()?.string()}")
            }

            loading.value = false

        }

    }
}
