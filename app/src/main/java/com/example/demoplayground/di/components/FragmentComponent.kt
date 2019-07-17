package com.example.demoplayground.di.components

import com.example.demoplayground.di.modules.FragmentModule
import com.example.demoplayground.di.FragmentScope
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent