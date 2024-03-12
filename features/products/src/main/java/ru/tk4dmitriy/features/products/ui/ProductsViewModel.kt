package ru.tk4dmitriy.features.products.ui

import io.reactivex.rxjava3.observers.DisposableSingleObserver
import ru.tk4dmitriy.core.ui.ViewModelMVI
import ru.tk4dmitriy.data.products.api.Product
import ru.tk4dmitriy.data.products.api.ProductsRepository
import ru.tk4dmitriy.features.products.di.FeatureProductsComponentHolder
import javax.inject.Inject

internal class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModelMVI<ProductsIntent, ProductsState>() {
    override fun handleIntent(intent: ProductsIntent) {
        when(intent) {
            ProductsIntent.Init -> {
                mState.onNext(ProductsState(loading = true))
                productsRepository.getProducts().subscribe(intentInitObserver)
            }

            ProductsIntent.LoadMore -> {
                if (!(mState.value?.loadingMore)!! && !(mState.value?.fullData)!!) {
                    mState.onNext(mState.value?.copy(loadingMore = true))
                    productsRepository.getProducts().subscribe(intentLoadMoreObserver)
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
            mState.onNext(mState.value?.copy(
                loading = false,
                data = t.toProductUi())
            )
        }

        override fun onError(e: Throwable) {
            mState.onNext(
                mState.value?.copy(
                    loading = false,
                    error = e
                )
            )
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
                    error = e
                )
            )
        }
    }

    private fun List<Product>.toProductUi() = map {
        ProductUi(
            id = it.id,
            title = it.title,
            description = it.description,
            thumbnails = it.thumbnail
        )
    }
}
