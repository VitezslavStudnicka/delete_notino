package com.vs.notino.main.product.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vs.notino.databinding.ItemProductBinding
import com.vs.notino.main.product.list.ProductDataAdapter.ProductItemViewHolder
import com.vs.notino.models.Product
import com.vs.notino.networking.RestRepository

class ProductDataAdapter(
    private val onDetailClick: ((Product) -> Unit)?,
    private val onFavClick: ((Product) -> Unit)?,
    private val onAddItemToBasketClick: ((Product) -> Unit)?
) : PagingDataAdapter<Product, ProductItemViewHolder>(DataDifferentiator) {

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onFavClick, onDetailClick, onAddItemToBasketClick)
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
            addToBasketClick: ((Product) -> Unit)?,
        ) {
            binding.product = product
            binding.detailClick = detailClick
            binding.addToBasketClick = addToBasketClick
            binding.favClick = {
                favClick?.invoke(it)
                // KekW, tento megahack prosim nepraktikovat. Je to spatne reseni na spatna data,
                // mela by chodit v responsu a tim padem byt i jedinym zdrojem pravdy a ne se udrzovat v pameti.
                // jiste by sel pouzit i two way databinding
                binding.product = it
                binding.executePendingBindings()
            }
            binding.ivImage.load(RestRepository.BASE_IMAGE_URL.plus(product.imageUrl)) {
                build()
            }
            binding.executePendingBindings()
        }
    }
}