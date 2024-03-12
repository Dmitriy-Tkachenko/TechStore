package ru.tk4dmitriy.features.products.ui

import android.util.Log
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import ru.tk4dmitriy.core.ui.ViewModelMVI
import ru.tk4dmitriy.data.products.api.Product
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.features.products.R
import ru.tk4dmitriy.features.products.di.FeatureProductsComponentHolder
import javax.inject.Inject

internal class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModelMVI<ProductsIntent, ProductsState, ProductsEffect>() {
    private val limit = 20
    override fun handleIntent(intent: ProductsIntent) {
        when(intent) {
            ProductsIntent.Init -> {
                mState.onNext(ProductsState(loading = true))
                productsRepository.getProducts(skip = 0, limit = limit)
                    .subscribe(intentInitObserver)
            }

            ProductsIntent.Refresh -> {
                productsRepository.getProducts(skip = 0, limit = limit)
                    .subscribe(intentRefreshObserver)
            }

            is ProductsIntent.LoadMore -> {
                if (!(mState.value?.loadingMore)!! && !(mState.value?.fullData)!!) {
                    mState.onNext(mState.value?.copy(loadingMore = true))
                    Log.d("ProductsViewModel", "${intent.skip}")
                    productsRepository.getProducts(skip = intent.skip, limit = limit)
                        .subscribe(intentLoadMoreObserver)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        FeatureProductsComponentHolder.reset()
    }

    private val intentInitObserver: DisposableSingleObserver<List<Product>>
        get() = object : DisposableSingleObserver<List<Product>>() {
        override fun onSuccess(t: List<Product>) {
            if (t.isNotEmpty()) {
                mState.onNext(
                    mState.value?.copy(
                        loading = false,
                        data = t.toProductUi()
                    )
                )
            } else mState.onNext(
                mState.value?.copy(
                    loading = false,
                    message = R.string.empty
                )
            )
        }

        override fun onError(e: Throwable) {
            mState.onNext(
                mState.value?.copy(loading = false, message = R.string.error)
            )
        }
    }

    private val intentRefreshObserver: DisposableSingleObserver<List<Product>>
        get() = object : DisposableSingleObserver<List<Product>>() {
            override fun onSuccess(t: List<Product>) {
                if (t.isNotEmpty()) {
                    mState.onNext(
                        mState.value?.copy(
                            loading = false,
                            loadingMore = false,
                            fullData = false,
                            data = t.toProductUi(),
                            message = null
                        )
                    )
                } else mState.onNext(
                    mState.value?.copy(
                        loading = false,
                        loadingMore = false,
                        fullData = false,
                        data = emptyList(),
                        message = R.string.empty
                    )
                )
            }

            override fun onError(e: Throwable) {
                mEffect.onNext(ProductsEffect.ShowToast(R.string.error))
            }
        }

    private val intentLoadMoreObserver: DisposableSingleObserver<List<Product>>
        get() = object : DisposableSingleObserver<List<Product>>() {
        override fun onSuccess(t: List<Product>) {
            if (t.isNotEmpty())
                mState.onNext(
                    mState.value?.copy(
                        loadingMore = false,
                        data = mState.value?.data!! + t.toProductUi()
                    )
                )
            else
                mState.onNext(
                    mState.value?.copy(
                        loadingMore = false,
                        fullData = true,
                        data = mState.value?.data!!
                    )
                )
        }
        override fun onError(e: Throwable) {
            mState.onNext(
                mState.value?.copy(
                    loadingMore = false,
                    data = mState.value?.data!!
                )
            )
            mEffect.onNext(ProductsEffect.ShowToast(R.string.error))
        }
    }

    private fun List<Product>.toProductUi() = map {
        ProductUi(
            id = it.id,
            title = it.title,
            description = it.description,
            thumbnails = it.thumbnail,
            price = it.price
        )
    }
}
