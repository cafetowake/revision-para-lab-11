package lab3moviles.uvg.edu.gt.funcionamiento.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.CharacterNavGraph
import lab3moviles.uvg.edu.gt.funcionamiento.main.character.list.CharactersDestination
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.LocationsNavGraph
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.list.LocationsDestination
import lab3moviles.uvg.edu.gt.funcionamiento.main.profile.ProfileDestination

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Any,
)

val navigationItems = listOf(
    NavItem(
        title = "Characters",
        selectedIcon = Icons.Filled.Groups,
        unselectedIcon = Icons.Outlined.Groups,
        destination = CharacterNavGraph
    ),
    NavItem(
        title = "Locations",
        selectedIcon = Icons.Filled.LocationOn,
        unselectedIcon = Icons.Outlined.LocationOn,
        destination = LocationsNavGraph
    ),
    NavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        destination = ProfileDestination
    )
)

val topLevelDestinations = listOf(
    CharactersDestination::class,
    LocationsDestination::class,
    ProfileDestination::class
)
