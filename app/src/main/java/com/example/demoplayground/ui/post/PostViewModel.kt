package com.example.demoplayground.ui.post

import androidx.lifecycle.MutableLiveData
import com.example.demoplayground.ui.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class PostViewModel(compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable) {

    val postListLiveData = MutableLiveData<List<Post>>()

    override fun onCreate() {

    }

    fun getPostList() {
        postListLiveData.postValue(getDummyPostList())
    }

    private fun getDummyPostList(): List<Post> {
        return ArrayList<Post>().apply {
            add(Post("Post 1"))
            add(Post("Post 2"))
            add(Post("Post 3"))
            add(Post("Post 4"))
            add(Post("Post 5"))
            add(Post("Post 6"))
            add(Post("Post 7"))
            add(Post("Post 8"))
            add(Post("Post 9"))
            add(Post("Post 10"))
            add(Post("Post 11"))
            add(Post("Post 12"))
            add(Post("Post 13"))
            add(Post("Post 14"))
            add(Post("Post 15"))
            add(Post("Post 16"))
            add(Post("Post 17"))
            add(Post("Post 18"))
            add(Post("Post 19"))
            add(Post("Post 20"))
            add(Post("Post 21"))
            add(Post("Post 22"))
            add(Post("Post 23"))
        }
    }

}