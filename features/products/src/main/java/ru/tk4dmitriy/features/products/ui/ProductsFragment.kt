package ru.tk4dmitriy.features.products.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        if (savedInstanceState == null) viewModel.dispatchIntent(ProductsIntent.Init)
        binding.rvProducts.addOnScrollListener(ProductsScrollListener {
            viewModel.dispatchIntent(ProductsIntent.LoadMore)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun observeOnState() {
        val disposable = viewModel.state.observeOn(AndroidSchedulers.mainThread()).subscribe {
            render(it)
        }
        compositeDisposable.add(disposable)
    }

    private fun render(state: ProductsState) {
        if (state.loading) {

        } else if (state.error != null) {

        } else adapter.submitList(state.data)
    }
}