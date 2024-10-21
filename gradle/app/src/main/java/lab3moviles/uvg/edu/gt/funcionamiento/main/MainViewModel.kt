package lab3moviles.uvg.edu.gt.funcionamiento.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lab3moviles.uvg.edu.gt.data.data.local.Entity.CharacterEntity
import lab3moviles.uvg.edu.gt.data.data.local.Entity.LocationEntity
import lab3moviles.uvg.edu.gt.repository.CharacterRepository
import lab3moviles.uvg.edu.gt.repository.LocationRepository

class MainViewModel(
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val characters = MutableLiveData<List<CharacterEntity>>()
    val locations = MutableLiveData<List<LocationEntity>>()

    fun fetchCharacters() {
        viewModelScope.launch {
            val characters = characterRepository.getAllCharacters()
            characters.postValue(characters)
        }
    }

    fun fetchLocations() {
        viewModelScope.launch {
            val locations = locationRepository.getAllLocations()
            locations.postValue(locations)
        }
    }

    fun insertCharacters(characters: List<CharacterEntity>) {
        viewModelScope.launch {
            characterRepository.insertAll(characters)
        }
    }

    fun insertLocations(locations: List<LocationEntity>) {
        viewModelScope.launch {
            locationRepository.insertAll(locations)
        }
    }
}
