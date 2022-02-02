package com.vs.notino.main.product.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.vs.notino.databinding.FragmentProductListBinding
import com.vs.notino.models.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private val viewModel by viewModels<ProductListViewModel>()

    private var _binding: FragmentProductListBinding? = null
    // Jasny, nemame not-null assertion operator, ale zde je to dokonce ukazkove pouziti od googlu vzhledem k DI
    private val binding get() = _binding!!

    private val productAdapter = ProductDataAdapter(::detailClick, ::favClick, ::addItemToBasket)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.productList.observe(this) {
            productAdapter.submitData(lifecycle, it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false).apply {
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupAdapter() {
        productAdapter.addLoadStateListener {
            val errorState = when {
                it.append is LoadState.Error -> it.append as LoadState.Error
                it.refresh is LoadState.Error -> it.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                // TODO UI reakce na error
            }
        }
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = productAdapter
        }
    }

    private fun setupRecyclerView() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun detailClick(product: Product) {
        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(
            product.productId.toLong()
        ))
    }

    private fun favClick(product: Product) {
        viewModel.doFavItem(product)
    }

    private fun addItemToBasket(product: Product) {
        viewModel.addItemToBasket(product)
    }
}