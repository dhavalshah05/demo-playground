package com.example.demoplayground.di.components

import com.example.demoplayground.PlaygroundApplication
import com.example.demoplayground.di.modules.ApplicationModule
import com.example.demoplayground.di.modules.viewmodel.ViewModelModule
import com.example.demoplayground.di.qualifiers.ApiKey
import com.example.demoplayground.ui.base.ViewModelFactory
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    // Provider methods
    @ApiKey
    fun getApiKey(): String

    fun getViewModelFactory(): ViewModelFactory

    // Inject
    fun inject(application: PlaygroundApplication)
}