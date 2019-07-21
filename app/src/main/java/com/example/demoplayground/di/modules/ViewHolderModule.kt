package com.example.demoplayground.di.modules

import androidx.lifecycle.LifecycleRegistry
import com.example.demoplayground.di.ViewHolderScope
import com.example.demoplayground.ui.base.lifecycleawarerecyclerview.BaseItemViewHolder
import com.example.demoplayground.ui.post.PostItemViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewHolderScope
    fun provideLifecycleRegistry(): LifecycleRegistry {
        return LifecycleRegistry(viewHolder)
    }

    @Provides
    fun providePostItemViewModel(compositeDisposable: CompositeDisposable): PostItemViewModel {
        return PostItemViewModel(compositeDisposable)
    }
}