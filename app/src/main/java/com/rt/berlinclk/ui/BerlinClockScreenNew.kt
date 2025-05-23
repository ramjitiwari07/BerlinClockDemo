import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rt.berlinclk.data.ClockUiState
import com.rt.berlinclk.model.BerlinClockViewModel


@Composable
fun BerlinerClockScreenWithInput(
    viewModel: BerlinClockViewModel = viewModel(),
    modifier: Modifier
) {
    val timeInput by viewModel.timeInput.collectAsState()
    val clockUiState by viewModel.clockUiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // 4. Add some padding for better aesthetics
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp, Alignment.CenterVertically
        ) // 5. Consistent spacing
    ) {
        // 6. Improved TextField
        OutlinedTextField( // Or TextField, OutlinedTextField is often preferred for clarity
            value = timeInput,
            onValueChange = { newTime ->
                viewModel.onTimeInputChange(newTime)
            },
            label = { Text("Enter Time (HH:MM:SS)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f) // Make it take up some width
        )

        Button(
            onClick = {
                // 7. Update clockUiState on button click
                viewModel.convertTimeToBerlinClock()
            }, modifier = Modifier.fillMaxWidth(0.6f) // Give button some width
        ) {
            Text("Convert to Berlin Clock") // 8. More descriptive button text
        }

        // 9. Displaying the Berlin Clock state (Consider a dedicated Composable)
        if (clockUiState.errorMessage != null) {
            Text(
                text = clockUiState.errorMessage!!,
                color = MaterialTheme.colorScheme.error, // Use theme color for errors
                style = MaterialTheme.typography.labelMedium
            )
        } else {
            // It's generally better to create a dedicated Composable for displaying the clock
            // to keep this Composable cleaner.
            BerlinClockDisplay(clockUiState)
        }
    }
}

//  Dedicated Composable for displaying the Berlin Clock
@Composable
fun BerlinClockDisplay(clockUiState: ClockUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between clock rows
    ) {
        // You would typically represent these with colored Boxes or similar UI elements
        // For simplicity, we're still using Text here.
        Text(clockUiState.seconds.name)
        Text(clockUiState.fiveHours.joinToString("") { it.name.first().toString() })
        Text(clockUiState.oneHour.joinToString("") { it.name.first().toString() })
        Text(clockUiState.fiveMinutes.joinToString("") { it.name.first().toString() })
        Text(clockUiState.oneMinute.joinToString("") { it.name.first().toString() }
        )
    }
}

// Preview for easy visualization in Android Studio
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme { // Assuming you're using Material Theme
        BerlinerClockScreenWithInput(modifier = Modifier)
    }
}