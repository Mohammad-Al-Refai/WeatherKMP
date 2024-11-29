package store

import com.arkivanov.mvikotlin.core.store.StoreFactory

class WeatherStore(
    private val storeFactory: StoreFactory,
    private val weatherStoreExecutorFactory: WeatherStoreExecutorFactory
) {
    val store = storeFactory.create(
        name = "Weather",
        initialState = State(),
        executorFactory = ::weatherStoreExecutorFactory,
        reducer = { intent ->
            when (intent) {
                is Msg.UpdateState -> intent.state
            }

        }
    )
}

sealed class Msg {
    data class UpdateState(var state: State) : Msg()
}
