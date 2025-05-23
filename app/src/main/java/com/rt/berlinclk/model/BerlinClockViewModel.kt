package com.rt.berlinclk.model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rt.berlinclk.data.ClockUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BerlinClockViewModel : ViewModel() {
    private val berlinClock = BerlinClock()

    private val _clockUiState = MutableStateFlow(ClockUiState())
    val clockUiState: StateFlow<ClockUiState> = _clockUiState

    private val _timeInput = MutableStateFlow("12:56:01")
    val timeInput: StateFlow<String> = _timeInput

    fun onTimeInputChange(newTime: String) {
        _timeInput.value = newTime
    }

    fun convertTimeToBerlinClock() {
        viewModelScope.launch {
            _clockUiState.value = berlinClock.convert(_timeInput.value)
        }
    }
}