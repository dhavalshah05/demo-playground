package com.example.demoplayground.di.components

import com.example.demoplayground.ui.main.MainActivity
import com.example.demoplayground.di.modules.ActivityModule
import com.example.demoplayground.di.ActivityScope
import com.example.demoplayground.ui.post.PostActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class])
interface ActivityComponent {

    // Inject
    fun inject(mainActivity: MainActivity)

    fun inject(mainActivity: PostActivity)
}