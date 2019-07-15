package com.example.demoplayground.di.modules

import android.app.Application
import com.example.demoplayground.di.qualifiers.ApiKey
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

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

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}