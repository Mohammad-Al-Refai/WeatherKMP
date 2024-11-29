package di

import api.WeatherAPI
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import store.WeatherStore
import store.WeatherStoreExecutorFactory
import org.koin.dsl.module

val commonModules = module {
    single<StoreFactory> {
        DefaultStoreFactory()
    }
    single {
        WeatherAPI(get())
    }
    single { WeatherStoreExecutorFactory(get()) }
    single {
        WeatherStore(storeFactory = get(), weatherStoreExecutorFactory = get())
    }
}