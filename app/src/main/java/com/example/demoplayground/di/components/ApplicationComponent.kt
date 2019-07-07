package com.example.demoplayground.di.components

import android.app.Application
import com.example.demoplayground.PlaygroundApplication
import com.example.demoplayground.di.modules.ApplicationModule
import com.example.demoplayground.di.qualifiers.ApiKey
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    // Inject
    fun inject(application: PlaygroundApplication)
}