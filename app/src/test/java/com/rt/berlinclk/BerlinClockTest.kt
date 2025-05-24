package com.rt.berlinclk

import com.rt.berlinclk.data.LampState
import com.rt.berlinclk.model.BerlinClock
import org.junit.Assert.assertEquals
import org.junit.Test

class BerlinClockTest {

    private val berlinClock = BerlinClock()

    @Test
    fun `test valid time conversion`() {
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
    fun `test invalid time format`() {
        val state = berlinClock.convert("13-17-01")
        assertEquals("Invalid time format. Use HH:MM:SS", state.errorMessage)
    }

    @Test
    fun `test out of range time`() {
        val state = berlinClock.convert("25:00:00")
        assertEquals("Invalid time value. Hours (0-23), Minutes (0-59), Seconds (0-59).", state.errorMessage)
    }

    @Test
    fun `test edge case midnight`() {
        val state = berlinClock.convert("00:00:00")
        assertEquals(LampState.R, state.seconds)
        assertEquals(listOf(LampState.O, LampState.O, LampState.O, LampState.O), state.fiveHours)
        assertEquals(listOf(LampState.O, LampState.O, LampState.O, LampState.O), state.oneHour)
        assertEquals(
            listOf(LampState.O, LampState.O, LampState.O, LampState.O, LampState.O, LampState.O,
                LampState.O, LampState.O, LampState.O, LampState.O, LampState.O),
            state.fiveMinutes
        )
        assertEquals(listOf(LampState.O, LampState.O, LampState.O, LampState.O), state.oneMinute)
    }
}