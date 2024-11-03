package lab3moviles.uvg.edu.gt.funcionamiento.main.location.list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import lab3moviles.uvg.edu.gt.data.dc.Location
import lab3moviles.uvg.edu.gt.funcionamiento.common.ErrorView
import lab3moviles.uvg.edu.gt.funcionamiento.common.LoadingView


@Composable
fun LocationsRoute(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationsViewModel = viewModel(factory = LocationsViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LocationsScreen(
        state = state,
        forceError = { viewModel.onEvent(LocationsEvent.ForceError) },
        onRetryClick = { viewModel.onEvent(LocationsEvent.RetryClick) },
        onLocationClick = onLocationClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun LocationsScreen(
    state: LocationsState,
    forceError: () -> Unit,
    onRetryClick: () -> Unit,
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when {
            state.isLoading -> {
                LoadingView(
                    loadingText = "Obteniendo ubicaciones",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { forceError() }
                )
            }

            state.isError -> {
                ErrorView(
                    errorText = "Uh, oh. Error al obtener ubicaciones",
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.locations) { item ->
                        LocationsItem(
                            location = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onLocationClick(item.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LocationsItem(
    location: Location,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)
    ) {
        Text(text = location.name)
        Text(
            text = location.type,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

private class LocationsParameterProvider : CollectionPreviewParameterProvider<LocationsState> (
    listOf(
        LocationsState(),
        LocationsState(
            isLoading = false,
            isError = true
        ),
        LocationsState(
            isLoading = false,
            locations = listOf(
                Location(
                    id = 1, name = "Jeffery Hartman", type = "consetetur", dimension = "mucius"
                ),
                Location(
                    id = 2, name = "Jeffery Hartman", type = "consetetur", dimension = "mucius"
                )
            )
        )
    )
)

