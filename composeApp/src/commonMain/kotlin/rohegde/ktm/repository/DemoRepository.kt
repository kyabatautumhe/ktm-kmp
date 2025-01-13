package rohegde.ktm.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import rohegde.ktm.network.ApiClient

class DemoRepository(
    private val httpClient: HttpClient = ApiClient.httpClient
) {

    suspend fun fetchData(): ApiResponse {
        return httpClient.get("https://jsonplaceholder.typicode.com/posts/1").body()
    }
}

@Serializable
data class ApiResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)