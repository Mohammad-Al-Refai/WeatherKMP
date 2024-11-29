package mo.kmp.weatherkmp.android.ui

import androidx.lifecycle.ViewModel
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import store.Intent
import store.State
import store.WeatherStore

class LandingViewModel(private val weatherStore: WeatherStore) : ViewModel() {
    private val store = weatherStore.store

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<State> = store.stateFlow

    init {
        onIntent(Intent.UpdateSearch("Jordan"))
        onIntent(Intent.Search)
    }
    fun onIntent(intent: Intent){
        store.accept(intent)
    }

    override fun onCleared() {
        super.onCleared()
        store.dispose()
    }
}