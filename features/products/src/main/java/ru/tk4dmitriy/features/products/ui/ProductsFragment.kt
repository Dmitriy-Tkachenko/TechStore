package ru.tk4dmitriy.features.products.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.tk4dmitriy.features.products.R
import ru.tk4dmitriy.features.products.databinding.FragmentProductsBinding
import ru.tk4dmitriy.features.products.di.FeatureProductsComponentHolder
import ru.tk4dmitriy.features.products.ui.utils.ProductItemDecoration
import ru.tk4dmitriy.features.products.ui.utils.ProductsScrollListener
import javax.inject.Inject

class ProductsFragment : Fragment(R.layout.fragment_products) {
    private lateinit var binding: FragmentProductsBinding
    private val compositeDisposable = CompositeDisposable()
    private val adapter: ProductsAdapter by lazy { ProductsAdapter() }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = viewModelFactory
        )[ProductsViewModel::class.java]
    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        viewModel.dispatchIntent(ProductsIntent.Refresh)
        binding.swipeRefresh.isRefreshing = false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureProductsComponentHolder.getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@ProductsFragment.adapter
            addItemDecoration(ProductItemDecoration())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeOnState()
        observeOnEffect()

        if (savedInstanceState == null) viewModel.dispatchIntent(ProductsIntent.Init)

        binding.apply {
            rvProducts.addOnScrollListener(ProductsScrollListener {
                viewModel.dispatchIntent(ProductsIntent.LoadMore(
                    skip = adapter.itemCount
                ))
            })
            swipeRefresh.setOnRefreshListener(refreshListener)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun observeOnState() {
        val disposable = viewModel.state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { render(it) }
        compositeDisposable.add(disposable)
    }

    private fun observeOnEffect() {
        val disposable = viewModel.effect
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { effect(it) }
        compositeDisposable.add(disposable)
    }

    private fun render(state: ProductsState) {
        if (state.loading) stateLoadingViewsVisibility()
        else if (state.loadingMore)
        else if (state.message != null)
            stateMessageViewsVisibility(message = resources.getString(state.message))
        else {
            stateDataViewsVisibility()
            adapter.submitList(state.data)
        }
    }

    private fun effect(effect: ProductsEffect) {
        when(effect) {
            is ProductsEffect.ShowToast ->
                Toast.makeText(requireActivity(), effect.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun stateLoadingViewsVisibility() {
        binding.apply {
            rvProducts.visibility = View.GONE
            tvMessage.visibility = View.GONE
            shimmer.visibility = View.VISIBLE
        }
    }

    private fun stateMessageViewsVisibility(message: String) {
        binding.apply {
            rvProducts.visibility = View.GONE
            shimmer.visibility = View.GONE
            tvMessage.visibility = View.VISIBLE
            tvMessage.text = message
        }
    }

    private fun stateDataViewsVisibility() {
        binding.apply {
            shimmer.visibility = View.GONE
            tvMessage.visibility = View.GONE
            rvProducts.visibility = View.VISIBLE
        }
    }
}