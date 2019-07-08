package com.example.demoplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoplayground.di.components.ActivityComponent
import com.example.demoplayground.di.components.DaggerActivityComponent
import com.example.demoplayground.di.modules.ActivityModule
import com.example.demoplayground.di.qualifiers.ApiKey
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var activityComponent: ActivityComponent

    @ApiKey
    @Inject
    lateinit var apiKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as PlaygroundApplication).getApplicationComponent())
                .build()

        activityComponent.inject(this)

        println("API Key: $apiKey")
    }

}
