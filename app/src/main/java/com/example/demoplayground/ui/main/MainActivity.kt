package com.example.demoplayground.ui.main

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.*
import androidx.core.animation.addListener
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
            startAnimationWithInterpolator()
        }
    }

    private fun startAlphaAnimation() {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.alpha)
        animator.setTarget(buttonLogin)

        animator as ObjectAnimator

        animator.addListener(
                onEnd = {

                },
                onStart = {
                    buttonLogin.visibility = View.VISIBLE
                },
                onRepeat = {

                },
                onCancel = {

                }
        )

        animator.addUpdateListener {
            println(it.animatedValue)
        }

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

    private fun startAlphaAnimationByCode() {
        val animator = ObjectAnimator.ofFloat(buttonLogin,
                "alpha",
                0.0f, 1.0f)
        animator.apply {
            duration = 1000
            start()
        }
    }

    private fun startMultipleAnimationByCode() {
        val rotateAnimation = ObjectAnimator.ofFloat(buttonLogin, "rotation", 0.0f, 360.0f)
        rotateAnimation.duration = 1000

        val scaleXAnimation = ObjectAnimator.ofFloat(buttonLogin, "scaleX", 1.0f, 1.5f)
        scaleXAnimation.duration = 1000

        val scaleYAnimation = ObjectAnimator.ofFloat(buttonLogin, "scaleY", 1.0f, 1.5f)
        scaleYAnimation.duration = 1000

        val scaleAnimationSet = AnimatorSet()
        scaleAnimationSet.playTogether(scaleXAnimation, scaleYAnimation)

        val rootAnimationSet = AnimatorSet()
        rootAnimationSet.playSequentially(rotateAnimation, scaleAnimationSet)

        rootAnimationSet.start()
    }

    private fun startMultipleAnimationByCodeWithChaining() {
        val rotateAnimation = ObjectAnimator.ofFloat(buttonLogin, "rotation", 0.0f, 360.0f)
        rotateAnimation.duration = 1000

        val scaleXAnimation = ObjectAnimator.ofFloat(buttonLogin, "scaleX", 1.0f, 1.5f)
        scaleXAnimation.duration = 1000

        val scaleYAnimation = ObjectAnimator.ofFloat(buttonLogin, "scaleY", 1.0f, 1.5f)
        scaleYAnimation.duration = 1000

        val scaleAnimationSet = AnimatorSet()
        scaleAnimationSet.play(scaleXAnimation).with(scaleYAnimation)

        val rootAnimationSet = AnimatorSet()
        rootAnimationSet.play(rotateAnimation).before(scaleAnimationSet)

        rootAnimationSet.start()
    }

    private fun startMultipleAnimationByCodeWithAnimationListener() {
        val rotateAnimation = ObjectAnimator.ofFloat(buttonLogin, "rotation", 0.0f, 360.0f)
        rotateAnimation.duration = 5000

        val scaleXAnimation = ObjectAnimator.ofFloat(buttonLogin, "scaleX", 1.0f, 1.5f)
        scaleXAnimation.duration = 500

        val scaleYAnimation = ObjectAnimator.ofFloat(buttonLogin, "scaleY", 1.0f, 1.5f)
        scaleYAnimation.duration = 500

        val scaleAnimationSet = AnimatorSet()
        scaleAnimationSet.playTogether(scaleXAnimation, scaleYAnimation)

        rotateAnimation.addUpdateListener {
            if((it.animatedValue as Float) > 180f) {
                scaleAnimationSet.start()
                rotateAnimation.removeAllUpdateListeners()
            }
        }

        rotateAnimation.start()
    }

    private fun startAnimationWithInterpolator() {
        val rotateAnimation = ObjectAnimator.ofFloat(buttonLogin, "rotation", 0.0f, 360.0f)
        rotateAnimation.duration = 1500
        rotateAnimation.interpolator = AccelerateInterpolator()
        rotateAnimation.start()
    }

}
