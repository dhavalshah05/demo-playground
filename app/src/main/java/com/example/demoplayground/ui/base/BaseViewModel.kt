package com.example.demoplayground.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(private val compositeDisposable: CompositeDisposable) : ViewModel() {

    /**
     *
     */
    val messageStringLiveData = MutableLiveData<String>()
    /**
     *
     */
    val messageStringIdLiveData = MutableLiveData<Int>()

    /**
     *
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    /**
     *
     */
    abstract fun onCreate()
}