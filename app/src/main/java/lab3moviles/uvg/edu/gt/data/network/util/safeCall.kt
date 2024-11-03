package lab3moviles.uvg.edu.gt.data.network.util

import io.ktor.client.statement.HttpResponse
import lab3moviles.uvg.edu.gt.domain.network.util.Result
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import lab3moviles.uvg.edu.gt.domain.network.util.NetworkError
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch(e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}