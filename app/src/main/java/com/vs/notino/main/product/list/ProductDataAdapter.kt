package com.vs.notino.main.product.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vs.notino.databinding.ItemProductBinding
import com.vs.notino.main.product.list.ProductDataAdapter.ProductItemViewHolder
import com.vs.notino.models.Product

class ProductDataAdapter(
    private val onDetailClick: ((Product) -> Unit)?,
    private val onFavClick: ((Product) -> Unit)?,
) : PagingDataAdapter<Product, ProductItemViewHolder>(DataDifferentiator) {

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onFavClick, onDetailClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductItemViewHolder(binding)
    }

    companion object DataDifferentiator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    class ProductItemViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            product: Product,
            favClick: ((Product) -> Unit)?,
            detailClick: ((Product) -> Unit)?,
        ) {
            binding.product = product
            binding.detailClick = detailClick
            binding.favClick = favClick
            binding.executePendingBindings()
        }
    }
}