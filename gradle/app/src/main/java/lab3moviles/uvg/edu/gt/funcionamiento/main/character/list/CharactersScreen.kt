package lab3moviles.uvg.edu.gt.funcionamiento.main.character.list

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import lab3moviles.uvg.edu.gt.data.data.local.Entity.CharacterEntity
import lab3moviles.uvg.edu.gt.data.dc.Person
import lab3moviles.uvg.edu.gt.funcionamiento.main.AuthViewModel
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.CharacterViewModel
import lab3moviles.uvg.edu.gt.ui.theme.Lab3movilesTheme

@Composable
fun CharactersRoute(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharactersViewModel = viewModel(factory = CharactersViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CharactersScreen(
        state = state,
        forceError = { viewModel.onEvent(CharactersEvent.ForceError) },
        onRetryClick = { viewModel.onEvent(CharactersEvent.RetryClick) },
        onCharacterClick = onCharacterClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun CharactersScreen(navController: NavController, authViewModel: AuthViewModel) {
    val characterViewModel: CharacterViewModel = hiltViewModel()
    val person by characterViewModel.characters.collectAsState()
    val isLoading by characterViewModel.isLoading.collectAsState()

    BackHandler {
        (navController.context as Activity).finish()
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(person) { person ->
                CharactersItem(person)
            }
        }
    }
}




@Composable
fun CharacterRow(character: CharacterEntity, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("character_detail/${character.id}")
            }
            .padding(16.dp)
    ) {
        Text(text = character.name, style = MaterialTheme.typography.headlineSmall)
    }
}


@Composable
private fun CharactersItem(
    person: Person,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = imageBackgroundColors[(person.id % (imageBackgroundColors.count() - 1))],
            shape = CircleShape
        ) {
            Box {
                Icon(
                    Icons.Outlined.Person, contentDescription = "Image",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Column {
            Text(text = person.name)
            Text(
                text = "${person.species} * ${person.status}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

private class CharactersParameterProvider : CollectionPreviewParameterProvider<CharactersState>(
    listOf(
        CharactersState(),
        CharactersState(
            isLoading = false,
            characters = listOf(
                Person(
                    id = 1,
                    name = "Carolyn Salinas",
                    status = "movet",
                    species = "iaculis",
                    gender = "parturient",
                    image = "quidam"
                ),
                Person(
                    id = 2,
                    name = "Carolyn Salinas",
                    status = "movet",
                    species = "iaculis",
                    gender = "parturient",
                    image = "quidam"
                )
            )
        ),
        CharactersState(
            isLoading = false,
            isError = true
        )
    )
)

@Preview
@Composable
private fun PreviewCharactersScreen(
    @PreviewParameter(CharactersParameterProvider::class) state: CharactersState
) {
    Lab3movilesTheme() {
        Surface {
            CharactersScreen(
                state = state,
                forceError = {},
                onRetryClick = {},
                onCharacterClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}