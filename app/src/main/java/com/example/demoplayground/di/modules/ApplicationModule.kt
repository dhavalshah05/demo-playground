package com.example.demoplayground.di.modules

import android.app.Application
import com.example.demoplayground.di.qualifiers.ApiKey
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private var application: Application) {

    @Provides
    fun providesApplication(): Application {
        return application
    }

    @ApiKey
    @Provides
    fun provideAPIKey(): String {
        return "ABC"
    }
}