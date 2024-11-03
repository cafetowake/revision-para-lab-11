package lab3moviles.uvg.edu.gt.data.network.util

import io.ktor.client.call.body
import lab3moviles.uvg.edu.gt.domain.network.util.Result
import io.ktor.client.statement.HttpResponse
import lab3moviles.uvg.edu.gt.domain.network.util.NetworkError as utilNetworkError

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result <T, utilNetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch(e: Exception) {
                Result.Error(utilNetworkError.SERIALIZATION)
            }
        }
        408 -> Result.Error(utilNetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(utilNetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(utilNetworkError.SERVER_ERROR)
        else -> Result.Error(utilNetworkError.UNKNOWN)
    }
}