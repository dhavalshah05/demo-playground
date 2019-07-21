package com.example.demoplayground.ui.base.lifecycleawarerecyclerview

import androidx.lifecycle.MutableLiveData
import com.example.demoplayground.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseItemViewModel<Item : Any>(compositeDisposable: CompositeDisposable)
    : BaseViewModel(compositeDisposable) {

    val itemLiveData = MutableLiveData<Item>()

    /**
     *
     */
    fun updateItem(item: Item) {
        this.itemLiveData.postValue(item)
    }

    /**
     *
     */
    fun clearViewModel() {
        super.onCleared()
    }

}