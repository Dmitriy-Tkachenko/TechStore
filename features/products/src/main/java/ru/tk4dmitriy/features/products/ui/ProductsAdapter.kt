package ru.tk4dmitriy.features.products.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tk4dmitriy.features.products.databinding.ItemProductBinding

class ProductsAdapter : ListAdapter<ProductUi, ProductsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding = itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item = item)
    }

    class ViewHolder(private val itemBinding: ItemProductBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind (item: ProductUi) = with(itemBinding) {
            title.text = item.title
            desc.text = item.description
            Glide.with(itemView.context)
                .load(item.thumbnails)
                .centerCrop()
                .into(thumb)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<ProductUi>() {
        override fun areItemsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductUi, newItem: ProductUi): Boolean {
            return oldItem == newItem
        }
    }
}