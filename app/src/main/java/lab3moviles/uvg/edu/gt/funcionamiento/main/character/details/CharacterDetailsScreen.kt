package lab3moviles.uvg.edu.gt.funcionamiento.main.character.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Person
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
import lab3moviles.uvg.edu.gt.data.dc.Person
import lab3moviles.uvg.edu.gt.funcionamiento.common.LoadingView

@Composable
fun CharacterDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: CharacterDetailsViewModel = viewModel(factory = CharacterDetailsViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CharacterDetailsScreen(
        state = state,
        onNavigateBack = onNavigateBack,
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailsScreen(
    state: CharacterDetailsState,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text("Character Detail")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        )
        CharacterDetailsContent(
            person = state.data,
            isLoading = state.isLoading,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun CharacterDetailsContent(
    person: Person?,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if (isLoading) {
            LoadingView(
                loadingText = "Obteniendo información",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            person?.let {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(
                                MaterialTheme.colorScheme.secondaryContainer,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "Person",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = person.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CharacterDetailsPropItem(
                        title = "Species:",
                        value = person.species,
                        modifier = Modifier.fillMaxWidth()
                    )
                    CharacterDetailsPropItem(
                        title = "Status:",
                        value = person.status,
                        modifier = Modifier.fillMaxWidth()
                    )
                    CharacterDetailsPropItem(
                        title = "Gender:",
                        value = person.gender,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterDetailsPropItem(
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

private class CharacterDetailsParameterProvider : CollectionPreviewParameterProvider<CharacterDetailsState>(
    listOf(
        CharacterDetailsState(),
        CharacterDetailsState(
            isLoading = false,
            data = Person(
                id = 2565,
                name = "Rick",
                status = "Alive",
                species = "Human",
                gender = "Male",
                image = ""
            )
        )
    )
)