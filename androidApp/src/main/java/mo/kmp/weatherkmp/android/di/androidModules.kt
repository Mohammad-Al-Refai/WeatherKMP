package mo.kmp.weatherkmp.android.di

import di.commonModules
import mo.kmp.weatherkmp.android.ui.LandingViewModel
import mo.kmp.weatherkmp.di.modules.sharedAndroidModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val androidModules = listOf(commonModules,sharedAndroidModules, module {
    viewModel {
        LandingViewModel(get())
    }
})