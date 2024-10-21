package lab3moviles.uvg.edu.gt.funcionamiento.main.location.list

sealed interface LocationsEvent {
    data object ForceError: LocationsEvent
    data object RetryClick: LocationsEvent
}