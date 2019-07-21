package com.example.demoplayground.di.components

import com.example.demoplayground.di.ViewHolderScope
import com.example.demoplayground.di.modules.ViewHolderModule
import com.example.demoplayground.ui.post.PostItemViewHolder
import dagger.Component

@ViewHolderScope
@Component(dependencies = [ApplicationComponent::class],
        modules = [ViewHolderModule::class])
interface ViewHolderComponent {

    // Inject here
    fun inject(itemViewHolder: PostItemViewHolder)
}