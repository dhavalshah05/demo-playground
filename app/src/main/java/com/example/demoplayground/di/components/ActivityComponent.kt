package com.example.demoplayground.di.components

import com.example.demoplayground.MainActivity
import com.example.demoplayground.di.modules.ActivityModule
import com.example.demoplayground.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class])
interface ActivityComponent {

    // Inject
    fun inject(mainActivity: MainActivity)
}