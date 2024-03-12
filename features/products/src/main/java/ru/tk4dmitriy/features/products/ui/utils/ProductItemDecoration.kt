package ru.tk4dmitriy.features.products.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.tk4dmitriy.features.products.R

class ProductItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        if(parent.getChildAdapterPosition(view) == 0)
            outRect.top = view.resources.getDimension(R.dimen.space_top).toInt()
        outRect.left = view.resources.getDimension(R.dimen.space_left).toInt()
        outRect.right = view.resources.getDimension(R.dimen.space_right).toInt()
        outRect.bottom = view.resources.getDimension(R.dimen.space_bottom).toInt()
    }
}