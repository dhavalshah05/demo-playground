package com.example.demoplayground.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.demoplayground.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideContext(): Context {
        return activity
    }

}