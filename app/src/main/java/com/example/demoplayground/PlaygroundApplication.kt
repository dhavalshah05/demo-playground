package com.example.demoplayground

import android.app.Application
import com.example.demoplayground.di.components.ApplicationComponent
import com.example.demoplayground.di.components.DaggerApplicationComponent
import com.example.demoplayground.di.modules.ApplicationModule
import com.example.demoplayground.di.qualifiers.ApiKey
import javax.inject.Inject
import javax.inject.Named

class PlaygroundApplication: Application() {

    @Inject
    @ApiKey
    lateinit var apiKey: String

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }

}