package com.example.demoplayground.ui.main

import android.os.Bundle
import com.example.demoplayground.R
import com.example.demoplayground.di.components.ActivityComponent
import com.example.demoplayground.di.qualifiers.ApiKey
import com.example.demoplayground.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @ApiKey
    @Inject
    lateinit var apiKey: String

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setUpView(savedInstanceState: Bundle?) {
        // SetUp view here
    }

}
