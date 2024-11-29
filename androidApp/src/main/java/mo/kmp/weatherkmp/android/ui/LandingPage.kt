package mo.kmp.weatherkmp.android.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import store.Intent


@Composable
fun LandingPage(viewModel: LandingViewModel) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = state.searchValue,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        viewModel.onIntent(intent = Intent.UpdateSearch(it))
                    })
                Button(onClick = {
                    viewModel.onIntent(Intent.Search)
                }) {
                    Text("Search")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            if (state.isError) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .fillMaxHeight(0.1f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colorScheme.error),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Unexpected error", color = MaterialTheme.colorScheme.onError)
                }
            }
            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.isSuccess) {
                PageContent(
                    countryName = state.countryName,
                    temp = state.temp,
                    feelsLike = state.feelsLike,
                    windSpeed = state.windSpeed
                )
            }

        }
    }
}
@Composable
fun PageContent(countryName: String, temp: Double, feelsLike: Double, windSpeed: Double) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = !visible
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(visible = visible, enter = fadeIn()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundedBox(
                    title = "Country",
                    content = countryName,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.padding(5.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RoundedBox(
                        title = "Temperature",
                        content = "${temp}C",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.padding(5.dp))
                    RoundedBox(
                        title = "Feels like",
                        content = "${feelsLike}C",
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.padding(5.dp))
                RoundedBox(
                    title = "Wind speed",
                    content = windSpeed.toString(),
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }
}

@Composable
fun RoundedBox(title: String, content: String, color: Color, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = color)
    ) {
        Text(
            title,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                content,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

