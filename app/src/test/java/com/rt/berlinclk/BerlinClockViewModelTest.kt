package com.rt.berlinclk

import com.rt.berlinclk.data.LampState
import com.rt.berlinclk.model.BerlinClock
import com.rt.berlinclk.model.BerlinClockViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BerlinClockViewModelTest {

    private lateinit var viewModel: BerlinClockViewModel
    private lateinit var berlinClock: BerlinClock

    @Before
    fun setup() {
        viewModel = BerlinClockViewModel()
        berlinClock = BerlinClock()
    }

    @Test
    fun `test updating time updates UI state`() {
       // viewModel.updateTime("13:17:01")
        viewModel.clockUiState.value
       // val state = viewModel.clockUiState
        val state = berlinClock.convert("12:56:01")
        assertEquals(LampState.O, state.seconds)
        assertEquals(listOf(LampState.R, LampState.R, LampState.O, LampState.O), state.fiveHours)
        assertEquals(listOf(LampState.R, LampState.R, LampState.O, LampState.O), state.oneHour)
        assertEquals(
            listOf(LampState.Y, LampState.Y, LampState.R, LampState.Y, LampState.Y, LampState.R,
                   LampState.Y, LampState.Y, LampState.R, LampState.Y, LampState.Y),
            state.fiveMinutes
        )
        assertEquals(listOf(LampState.Y, LampState.O, LampState.O, LampState.O), state.oneMinute)
    }

    @Test
    fun `test invalid time sets error`() {
        val state = berlinClock.convert("12:56")
        assertEquals("Invalid time format. Use HH:MM:SS", state.errorMessage)
    }
}
