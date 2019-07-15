package com.example.demoplayground.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demoplayground.PlaygroundApplication
import com.example.demoplayground.di.components.ActivityComponent
import com.example.demoplayground.di.components.DaggerActivityComponent
import com.example.demoplayground.di.modules.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(createActivityComponent())
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
        setContentView(getLayoutId())
        observeLiveData()
        setUpView(savedInstanceState)
        viewModel.onCreate()
    }

    /**
     *
     */
    protected open fun observeLiveData() {
        viewModel.messageStringLiveData.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.messageStringIdLiveData.observe(this, Observer { stringId ->
            Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
        })
    }

    /**
     *
     */
    private fun createActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .applicationComponent((application as PlaygroundApplication).getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build()
    }

    /**
     *
     */
    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    /**
     *
     */
    protected abstract fun getViewModelClass(): Class<VM>

    /**
     *
     */
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    /**
     *
     */
    protected abstract fun setUpView(savedInstanceState: Bundle?)

}