package com.example.demoplayground.ui.base.lifecycleawarerecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.example.demoplayground.PlaygroundApplication
import com.example.demoplayground.di.components.DaggerViewHolderComponent
import com.example.demoplayground.di.components.ViewHolderComponent
import com.example.demoplayground.di.modules.ViewHolderModule
import javax.inject.Inject

abstract class BaseItemViewHolder<Item : Any, VM : BaseItemViewModel<Item>>(
        @LayoutRes layoutId: Int,
        parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)),
        LifecycleOwner {

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    init {
        onCreate()
    }


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    /**
     *
     */
    open fun bind(item: Item) {
        viewModel.updateItem(item)
    }

    /**
     *
     */
    protected fun onCreate() {
        injectDependencies(createViewHolderComponent())
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        observeLiveData()
        setUpView(itemView)
    }

    /**
     *
     */
    fun onStart() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    /**
     *
     */
    fun onStop() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    /**
     *
     */
    fun onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    /**
     *
     */
    private fun createViewHolderComponent(): ViewHolderComponent =
            DaggerViewHolderComponent.builder()
                    .applicationComponent((itemView.context.applicationContext as PlaygroundApplication).getApplicationComponent())
                    .viewHolderModule(ViewHolderModule(this))
                    .build()

    /**
     *
     */
    protected open fun observeLiveData() {
        // NO-OP
    }

    /**
     *
     */
    protected abstract fun injectDependencies(component: ViewHolderComponent)

    /**
     *
     */
    protected abstract fun setUpView(view: View)
}