package com.example.demoplayground.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.example.demoplayground.ui.base.ViewModelFactory
import com.example.demoplayground.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(creators)
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(compositeDisposable: CompositeDisposable): ViewModel {
        return MainViewModel(compositeDisposable)
    }

}