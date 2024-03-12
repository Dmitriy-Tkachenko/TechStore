package ru.tk4dmitriy.features.products.ui.utils

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsScrollListener(
    private val dispatchIntentLoadMore: () -> Unit
): RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val linearLayoutManager = (recyclerView.layoutManager as LinearLayoutManager)
        val visibleItemCount = linearLayoutManager.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

        loadMore(visibleItemCount, firstVisibleItemPosition, totalItemCount)
    }

    private fun loadMore(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ) {
        val remainingItemCount = totalItemCount - firstVisibleItemPosition - visibleItemCount
        val triggerThreshold = 5

        if (remainingItemCount <= triggerThreshold) dispatchIntentLoadMore()
    }
}