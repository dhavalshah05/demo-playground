package com.example.demoplayground.ui.post

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoplayground.R
import com.example.demoplayground.di.components.ActivityComponent
import com.example.demoplayground.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : BaseActivity<PostViewModel>() {

    private lateinit var adapter: PostAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getViewModelClass(): Class<PostViewModel> {
        return PostViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_post
    }

    override fun setUpView(savedInstanceState: Bundle?) {
        adapter = PostAdapter(lifecycle)
        layoutManager = LinearLayoutManager(this)

        setUpRecyclerViewPostList()
        viewModel.getPostList()
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.postListLiveData.observe(this, Observer { adapter.replaceData(it) })
    }

    private fun setUpRecyclerViewPostList() {
        recycleViewPostList.layoutManager = layoutManager
        recycleViewPostList.adapter = adapter
    }

}
