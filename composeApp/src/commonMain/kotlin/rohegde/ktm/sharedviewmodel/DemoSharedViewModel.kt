package rohegde.ktm.sharedviewmodel

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rohegde.ktm.repository.ApiResponse
import rohegde.ktm.repository.DemoRepository

class DemoSharedViewModel(
    private val demoRepository: DemoRepository
) {

    private var _apiResponse = MutableStateFlow<ApiResponse?>(null)
    val apiResponse: StateFlow<ApiResponse?> get() = _apiResponse

    fun getApiData() {

        val apiDataExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            // Handle exception here
        }

        CoroutineScope(Dispatchers.Main + apiDataExceptionHandler).launch {
            _apiResponse.value = demoRepository.fetchData()
        }
    }
}