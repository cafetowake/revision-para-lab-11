package lab3moviles.uvg.edu.gt.domain.Api

import lab3moviles.uvg.edu.gt.data.network.dto.character.CharacterDetailsDto
import lab3moviles.uvg.edu.gt.data.network.dto.character.CharactersDto
import lab3moviles.uvg.edu.gt.data.network.dto.location.LocationDetailsDto
import lab3moviles.uvg.edu.gt.data.network.dto.location.LocationsDto
import lab3moviles.uvg.edu.gt.domain.network.util.NetworkError
import lab3moviles.uvg.edu.gt.domain.network.util.Result

interface ShowApi {
    suspend fun getAllCharacters(): Result<CharactersDto, NetworkError>
    suspend fun getCharacterById(id: Int): Result<CharacterDetailsDto, NetworkError>
    suspend fun getAllLocations(): Result<LocationsDto, NetworkError>
    suspend fun getLocationById(id: Int): Result<LocationDetailsDto, NetworkError>
}