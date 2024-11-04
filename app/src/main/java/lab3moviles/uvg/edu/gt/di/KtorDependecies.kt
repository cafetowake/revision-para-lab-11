package lab3moviles.uvg.edu.gt.di

import android.app.Application
import io.ktor.client.HttpClient
import lab3moviles.uvg.edu.gt.data.network.HttpClientFactory

object KtorDependencies {
    private var httpClient: HttpClient? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    fun provideHttpClient(context: Application): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}