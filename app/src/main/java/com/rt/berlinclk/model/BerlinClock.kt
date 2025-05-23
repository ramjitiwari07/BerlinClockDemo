package com.rt.berlinclk.model

import com.rt.berlinclk.data.ClockUiState
import com.rt.berlinclk.data.LampState

class BerlinClock {
    fun convert(timeString: String): ClockUiState {
        val parts = timeString.split(":")
        if (parts.size != 3) {
            return ClockUiState(errorMessage = "Invalid time format. Use HH:MM:SS")
        }

        val hours = parts[0].toIntOrNull()
        val minutes = parts[1].toIntOrNull()
        val seconds = parts[2].toIntOrNull()

        if (hours == null || minutes == null || seconds == null ||
            hours !in 0..23 || minutes !in 0..59 || seconds !in 0..59
        ) {
            return ClockUiState(errorMessage = "Invalid time value. Hours (0-23), Minutes (0-59), Seconds (0-59).")
        }

        // ... rest of the conversion logic
        return ClockUiState(
            seconds = if (seconds % 2 == 0) LampState.R else LampState.O,
            fiveHours = calculateLampStates(hours / 5, 4, LampState.R, LampState.O),
            oneHour = calculateLampStates(hours % 5, 4, LampState.R, LampState.O),
            fiveMinutes = calculateFiveMinuteLampStates(
                minutes / 5,
                11,
                LampState.Y,
                LampState.O
            ), // Special handling for fiveMinutes
            oneMinute = calculateLampStates(minutes % 5, 4, LampState.Y, LampState.O)
        )
    }

    private fun calculateLampStates(
        activeCount: Int,
        totalLamps: Int,
        activeState: LampState,
        inactiveState: LampState
    ): List<LampState> {
        return List(activeCount) { activeState } + List(totalLamps - activeCount) { inactiveState }
    }

    // Special handling for the five-minute row as the 3rd, 6th, 9th lamps are Red (R) if active.
    private fun calculateFiveMinuteLampStates(
        activeMinutesInFiveBlock: Int, // How many 5-minute blocks are lit
        totalLamps: Int,
        activeYellowState: LampState, // Typically Y
        inactiveState: LampState      // Typically O
    ): List<LampState> {
        val redState = LampState.R // Special state for 3rd, 6th, 9th
        return List(totalLamps) { index ->
            if (index < activeMinutesInFiveBlock) {
                if ((index + 1) % 3 == 0) redState else activeYellowState
            } else {
                inactiveState
            }
        }
    }

}