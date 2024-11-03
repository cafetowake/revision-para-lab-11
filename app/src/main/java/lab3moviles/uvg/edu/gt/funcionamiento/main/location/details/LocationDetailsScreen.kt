package lab3moviles.uvg.edu.gt.funcionamiento.main.location.details


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import lab3moviles.uvg.edu.gt.data.dc.Location
import lab3moviles.uvg.edu.gt.funcionamiento.common.LoadingView

@Composable
fun LocationDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: LocationDetailsViewModel = viewModel(factory = LocationDetailsViewModel.Factory)
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LocationDetailsScreen(
        state = state,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationDetailsScreen(
    state: LocationDetailsState,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text("Location Details")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        )
        LocationDetailsContent(
            location = state.data,
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Composable
private fun LocationDetailsContent(
    location: Location?,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (isLoading) {
            LoadingView(
                loadingText = "Obteniendo información",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            location?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = location.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LocationDetailsPropItem(
                        title = "ID:",
                        value = location.id.toString(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    LocationDetailsPropItem(
                        title = "Type:",
                        value = location.type,
                        modifier = Modifier.fillMaxWidth()
                    )
                    LocationDetailsPropItem(
                        title = "Dimensions:",
                        value = location.dimension,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun LocationDetailsPropItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}

private class LocationDetailsParameterProvider : CollectionPreviewParameterProvider<LocationDetailsState>(
    listOf(
        LocationDetailsState(),
        LocationDetailsState(
            data = Location(id = 4458, name = "Christine Walls", type = "felis", dimension = "nam"),
            isLoading = false
        )
    )
)

