package com.example.demoplayground.ui.main

import com.example.demoplayground.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable) {

    override fun onCreate() {
        messageStringLiveData.postValue("Hello from MainViewModel")
    }

}