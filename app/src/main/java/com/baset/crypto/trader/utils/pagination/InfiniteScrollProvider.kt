package com.baset.crypto.trader.utils.pagination

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class InfiniteScrollProvider : RecyclerView.OnScrollListener() {
    /**
     * used to determine currently user is waiting for loading items or not
     */
    private var isLoading = false

    /**
     * used to determine all pages loaded or not
     */
    abstract fun isLastPage(): Boolean

    /**
     * a callback for load more items
     */
    abstract fun onLoadMore()

    /**
     * position of last visible item
     */
    private var lastVisibleItem = 0

    /**
     * total items count of [.recyclerView]
     */
    private var totalItemCount = 0

    /**
     * total items count, relate to before last [.onLoadMoreListener] call.
     */
    private var previousItemCount = 0

    companion object {
        /**
         * [.onLoadMoreListener] called when [.recyclerView] reach to item with position [.totalItemCount] - {@value THRESHOLD}
         */
        const val THRESHOLD = 3
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager!!
        totalItemCount = layoutManager.itemCount
        if (previousItemCount > totalItemCount) {
            previousItemCount = totalItemCount - THRESHOLD
        }
        when (layoutManager) {
            is GridLayoutManager -> {
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
            is StaggeredGridLayoutManager -> {
                val spanCount = layoutManager.spanCount
                val ids = intArrayOf(spanCount)
                layoutManager.findLastVisibleItemPositions(ids)
                var max = ids[0]
                for (i in 1 until ids.size) {
                    if (ids[1] > max) {
                        max = ids[1]
                    }
                }
                lastVisibleItem = max
            }
        }
        if (totalItemCount > THRESHOLD) {
            if (previousItemCount <= totalItemCount && isLoading)
                isLoading = false
            if (!isLoading && (lastVisibleItem > (totalItemCount - THRESHOLD)) && totalItemCount > previousItemCount && !isLastPage()) {
                onLoadMore()
                isLoading = true
                previousItemCount = totalItemCount
            }
        }
        super.onScrolled(recyclerView, dx, dy)
    }
}