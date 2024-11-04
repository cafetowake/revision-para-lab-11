package lab3moviles.uvg.edu.gt.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import lab3moviles.uvg.edu.gt.data.network.dto.character.CharacterDetailsDto
import lab3moviles.uvg.edu.gt.data.network.dto.character.CharactersDto
import lab3moviles.uvg.edu.gt.data.network.dto.location.LocationDetailsDto
import lab3moviles.uvg.edu.gt.data.network.dto.location.LocationsDto
import lab3moviles.uvg.edu.gt.domain.network.util.Result
import lab3moviles.uvg.edu.gt.data.network.util.safeCall
import lab3moviles.uvg.edu.gt.domain.Api.ShowApi
import lab3moviles.uvg.edu.gt.domain.network.util.NetworkError

class KtorShowApi(
    private val httpClient: HttpClient
): ShowApi {
    override suspend fun getAllCharacters(): Result<CharactersDto, NetworkError> {
        return safeCall {
            httpClient.get("https://rickandmortyapi.com/api/character")
        }
    }

    override suspend fun getCharacterById(id: Int): Result<CharacterDetailsDto, NetworkError> {
        return safeCall {
            httpClient.get("https://rickandmortyapi.com/api/character/$id")
        }
    }

    override suspend fun getAllLocations(): Result<LocationsDto, NetworkError> {
        return safeCall {
            httpClient.get("https://rickandmortyapi.com/api/location")
        }
    }

    override suspend fun getLocationById(id: Int): Result<LocationDetailsDto, NetworkError> {
        return safeCall {
            httpClient.get("https://rickandmortyapi.com/api/location/$id")
        }
    }
}
