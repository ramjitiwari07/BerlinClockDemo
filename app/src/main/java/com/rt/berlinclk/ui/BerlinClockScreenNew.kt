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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp, Alignment.CenterVertically
        )
    ) {
        OutlinedTextField(
            value = timeInput,
            onValueChange = { newTime ->
                viewModel.onTimeInputChange(newTime)
            },
            label = { Text("Enter Time (HH:MM:SS)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Button(
            onClick = {
                viewModel.convertTimeToBerlinClock()
            }, modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("Convert to Berlin Clock")
        }

        // Displaying the Berlin Clock state
        if (clockUiState.errorMessage != null) {
            Text(
                text = clockUiState.errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium
            )
        } else {
            BerlinClockDisplay(clockUiState)
        }
    }
}

@Composable
fun BerlinClockDisplay(clockUiState: ClockUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing b/w clock rows
    ) {
        Text(clockUiState.seconds.name)
        Text(clockUiState.fiveHours.joinToString("") { it.name.first().toString() })
        Text(clockUiState.oneHour.joinToString("") { it.name.first().toString() })
        Text(clockUiState.fiveMinutes.joinToString("") { it.name.first().toString() })
        Text(clockUiState.oneMinute.joinToString("") { it.name.first().toString() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        BerlinerClockScreenWithInput(modifier = Modifier)
    }
}
