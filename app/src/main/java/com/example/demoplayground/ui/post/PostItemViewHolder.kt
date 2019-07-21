package com.example.demoplayground.ui.post

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.demoplayground.R
import com.example.demoplayground.di.components.ViewHolderComponent
import com.example.demoplayground.ui.base.lifecycleawarerecyclerview.BaseItemViewHolder
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostItemViewHolder(parent: ViewGroup) : BaseItemViewHolder<Post, PostItemViewModel>(R.layout.post_list_item, parent) {

    override fun injectDependencies(component: ViewHolderComponent) {
        component.inject(this)
    }

    override fun setUpView(view: View) {
        // NO-OP
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.itemLiveData.observe(this, Observer { post ->
            itemView.textViewPostTitle.text = post.title
        })
    }
}