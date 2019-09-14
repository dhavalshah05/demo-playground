package com.example.demoplayground.ui.main

import android.animation.AnimatorInflater
import android.os.Bundle
import com.example.demoplayground.R
import com.example.demoplayground.di.components.ActivityComponent
import com.example.demoplayground.di.qualifiers.ApiKey
import com.example.demoplayground.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
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
        buttonStartAnimation.setOnClickListener {
            startTranslateAnimation()
        }
    }

    private fun startAlphaAnimation() {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
        animator.setTarget(buttonLogin)
        animator.start()
    }

    private fun startRotateAnimation() {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.rotate)
        animator.setTarget(buttonLogin)
        animator.start()
    }

    private fun startScaleAnimation() {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.scale)
        animator.setTarget(buttonLogin)
        animator.start()
    }

    private fun startTranslateAnimation() {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.translate)
        animator.setTarget(buttonLogin)
        animator.start()
    }
}
