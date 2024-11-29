package mo.kmp.weatherkmp.android

import android.app.Application
import mo.kmp.weatherkmp.android.di.androidModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(androidModules)
        }
    }
}

