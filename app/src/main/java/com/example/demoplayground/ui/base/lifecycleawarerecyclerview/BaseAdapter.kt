package com.example.demoplayground.ui.base.lifecycleawarerecyclerview

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
abstract class BaseAdapter<Item : Any, VH : BaseItemViewHolder<Item, out BaseItemViewModel<Item>>>(
        parentLifecycle: Lifecycle
) : RecyclerView.Adapter<VH>() {

    private val itemList = ArrayList<Item>()
    private var recyclerView: RecyclerView? = null

    init {
        parentLifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onParentDestroy() {
                recyclerView?.run {
                    for (i in 0 until childCount) {
                        getChildAt(i)?.let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).run {
                                onDestroy()
                                viewModel.clearViewModel()
                            }
                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onParentStop() {
                recyclerView?.run {
                    for (i in 0 until childCount) {
                        getChildAt(i)?.let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).onStop()
                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onParentStart() {
                recyclerView?.run {
                    if (layoutManager is LinearLayoutManager) {
                        val first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        val last = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (first in 0..last) {
                            for (i in first..last) {
                                findViewHolderForAdapterPosition(i)?.let {
                                    (it as BaseItemViewHolder<*, *>).onStart()
                                }
                            }
                        }
                    }
                }
            }

        })
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onViewAttachedToWindow(holder: VH) {
        holder.onStart()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        holder.onStop()
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        Log.i("BaseAdapter", "VH recycled: ${holder.hashCode()}")
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(itemList[position])
    }

    /**
     *
     */
    fun replaceData(items: List<Item>) {
        this.itemList.clear()
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    /**
     *
     */
    fun appendData(items: List<Item>) {
        //this.itemList.addAll(items)
    }
}