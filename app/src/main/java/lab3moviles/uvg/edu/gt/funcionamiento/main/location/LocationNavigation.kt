package lab3moviles.uvg.edu.gt.funcionamiento.main.location

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.details.locationDetailsScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.details.navigateToLocationDetailsScreen
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.list.LocationsDestination
import lab3moviles.uvg.edu.gt.funcionamiento.main.location.list.locationsScreen

@Serializable
data object LocationsNavGraph

fun NavController.navigateToLocationsGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationsNavGraph, navOptions)
}

fun NavGraphBuilder.locationsGraph(
    navController: NavController
) {
    navigation<LocationsNavGraph>(
        startDestination = LocationsDestination
    ) {
        locationsScreen(
            onLocationClick = navController::navigateToLocationDetailsScreen
        )
        locationDetailsScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}