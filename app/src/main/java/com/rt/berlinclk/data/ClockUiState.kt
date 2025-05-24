package com.rt.berlinclk.data



data class ClockUiState(
    val seconds: LampState = LampState.O,
    val fiveHours: List<LampState> = emptyList(),
    val oneHour: List<LampState> = emptyList(),
    val fiveMinutes: List<LampState> = emptyList(),
    val oneMinute: List<LampState> = emptyList(),
    val errorMessage: String? = "error_message" // For displaying errors
)
