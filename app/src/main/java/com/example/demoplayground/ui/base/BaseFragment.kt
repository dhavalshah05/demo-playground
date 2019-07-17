package com.example.demoplayground.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demoplayground.PlaygroundApplication
import com.example.demoplayground.di.components.DaggerFragmentComponent
import com.example.demoplayground.di.components.FragmentComponent
import com.example.demoplayground.di.modules.FragmentModule
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(createFragmentComponent())
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
        observeLiveData()
        viewModel.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(view, savedInstanceState)
    }

    /**
     *
     */
    protected open fun observeLiveData() {
        viewModel.messageStringLiveData.observe(this, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        viewModel.messageStringIdLiveData.observe(this, Observer { stringId ->
            Toast.makeText(requireContext(), getString(stringId), Toast.LENGTH_SHORT).show()
        })
    }

    /**
     *
     */
    private fun createFragmentComponent(): FragmentComponent {
        return DaggerFragmentComponent.builder()
            .applicationComponent((activity!!.application as PlaygroundApplication).getApplicationComponent())
            .fragmentModule(FragmentModule(this))
            .build()
    }

    /**
     *
     */
    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

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
    protected abstract fun setUpView(view: View, savedInstanceState: Bundle?)


}