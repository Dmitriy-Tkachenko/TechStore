package ru.tk4dmitriy.features.products.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.tk4dmitriy.features.products.R
import javax.inject.Inject

class ProductsFragment : Fragment(R.layout.products_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = viewModelFactory
        )[ProductsViewModel::class.java]
    }
}