package rohegde.ktm.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

object ApiClient {

    val httpClient = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json() // Use Kotlinx serialization
        }
    }
}