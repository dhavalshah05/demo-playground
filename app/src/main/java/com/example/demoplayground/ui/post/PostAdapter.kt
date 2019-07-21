package com.example.demoplayground.ui.post

import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.demoplayground.ui.base.lifecycleawarerecyclerview.BaseAdapter

class PostAdapter(parentLifecycle: Lifecycle) : BaseAdapter<Post, PostItemViewHolder>(parentLifecycle) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val viewHolder = PostItemViewHolder(parent)
        Log.i("BaseAdapter", "VH created: ${viewHolder.hashCode()}")
        return viewHolder
    }

}