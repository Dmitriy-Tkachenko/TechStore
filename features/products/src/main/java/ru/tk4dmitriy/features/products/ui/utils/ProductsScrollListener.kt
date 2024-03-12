package ru.tk4dmitriy.features.products.ui.utils

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
        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
            dispatchIntentLoadMore()
        }
    }
}